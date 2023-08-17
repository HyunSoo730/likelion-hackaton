package com.example.lionproject.Batch;

import com.example.lionproject.Batch.Reader.SenuriServiceDetailReader;
import com.example.lionproject.OpenApi.CallOpenApi;
import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceDetailRawResponse;
import com.example.lionproject.controller.kakao.KakaoMessageFeedRequest;
import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.kakao.KakaoMemberFav;
import com.example.lionproject.repository.kakao.KakaoMemberFavRepository;
import com.example.lionproject.repository.senuri.SenuriServiceDetailRepository;
import com.example.lionproject.service.kakao.KakaoMessageService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SenuriKakaoNotificationJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final KakaoMessageService kakaoMessageService;

    private final SenuriServiceDetailRepository senuriRepository;
    private final KakaoMemberFavRepository kakaoFavRepository;

    @Bean
    public Job senuriKakaoNotificationJob(Step fetchAllKakaoMembers, Step sendKakaoNotification) {
        log.info("[SenuriKakaoNotificationJob] Job Launched");

        return new JobBuilder("senuriKakaoNotificationJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(fetchAllKakaoMembers).next(sendKakaoNotification)
                .build();
    }

    @Bean
    @JobScope
    public Step fetchAllKakaoMembers(Tasklet fetchAllKakaoMembersTasklet) {
        return new StepBuilder("fetchAllKakaoMembers", jobRepository)
                .allowStartIfComplete(true)
                .tasklet(fetchAllKakaoMembersTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    @JobScope
    public Step sendKakaoNotification(ItemReader<KakaoMemberAndSenuri> kakaoMemberFavItemReader,
                                      ItemProcessor<KakaoMemberAndSenuri, List<KakaoMessageFeedAndToken>> senuriKakaoNotificationProcessor,
                                      ItemWriter<List<KakaoMessageFeedAndToken>> senuriKakaoNotificationWriter) {
        log.info("[SenuriKakaoNotificationJob] Insert Info Step Launched");

        return new StepBuilder("fetchSenuriServiceDetailStep", jobRepository)
                .allowStartIfComplete(true)
                .<KakaoMemberAndSenuri, List<KakaoMessageFeedAndToken>>chunk(5, platformTransactionManager)
                .reader(kakaoMemberFavItemReader)
                .processor(senuriKakaoNotificationProcessor)
                .writer(senuriKakaoNotificationWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<KakaoMemberAndSenuri> kakaoMemberFavItemReader(@Value("#{jobExecutionContext['members']}") List<Long> members) {
        return new ItemReader<KakaoMemberAndSenuri>() {

            private final int size = members.size();
            private int index = 0;
            private String today = LocalDate.now().minusDays(7L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            @Override
            public KakaoMemberAndSenuri read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if (index >= size) {
                    return null;
                }

                List<KakaoMemberFav> favs = kakaoFavRepository.findAllByKakaoMemberUserId(members.get(index));
                index++;
                if (favs.isEmpty() || favs.size() == 0){
                    return KakaoMemberAndSenuri.of(null, null);
                }
                KakaoMember member = favs.get(0).getKakaoMember();
                List<SenuriServiceDetailCheck> senuriServiceDetailChecks = favs.stream()
                        .map(f -> senuriRepository.findCreateDayAfterTodayAndCityOrderByToAcptDdAsc(today, f.getAreaName()))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());

                return KakaoMemberAndSenuri.of(member, senuriServiceDetailChecks);
            }

        };
    }

    @Bean
    @StepScope
    public ItemProcessor<KakaoMemberAndSenuri, List<KakaoMessageFeedAndToken>> senuriKakaoNotificationProcessor() {

        return item -> {
            if(item.kakaoMember == null){
                return new ArrayList<>();
            }

            String accessToken = item.getKakaoMember().getAccessToken();

            return item.getSenuriServiceDetailCheck().stream()
                    .map(s -> {
                        KakaoMessageFeedRequest request = new KakaoMessageFeedRequest();
                        request.setObject_type("feed");
                        request.updateContent("회원님의 관심 지역에 새로운 구직 정보가 등록됐어요!",
                                "",
                                "https://ifh.cc/g/CG3xKK.png",
                                640,
                                300,
                                "http://www.naver.com",
                                "www.naver.com",
                                "contentId=100",
                                "contentId=100");
                        request.updateItemContent("채용제목", s.getWantedTitle(),
                                "채용공고형태", s.getEmplymShpNm(),
                                "접수방법", s.getAcptMthdCd(),
                                "담장자 연락처", s.getClerkContt());

                        return new KakaoMessageFeedAndToken(accessToken, request);
                    }).collect(Collectors.toList());
        };
    }

    @Bean
    @StepScope
    public ItemWriter<List<KakaoMessageFeedAndToken>> senuriKakaoNotificationWriter() {
        log.info("[SenuriKakaoNotificationWriter] Writer");

        return items -> items.forEach(i ->
                i.stream().filter(ii -> ii.accessToken != null)
                                .forEach(ii -> kakaoMessageService.sendFeedMessage(ii.getAccessToken(), ii.getKakaoMessageFeedRequest())));
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class KakaoMemberAndSenuri{

        private KakaoMember kakaoMember;
        private List<SenuriServiceDetailCheck> senuriServiceDetailCheck;

        public static KakaoMemberAndSenuri of(KakaoMember kakaoMember, List<SenuriServiceDetailCheck> senuriServiceDetailCheck) {
            return new KakaoMemberAndSenuri(kakaoMember, senuriServiceDetailCheck);
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class KakaoMessageFeedAndToken{

        private String accessToken;
        private KakaoMessageFeedRequest kakaoMessageFeedRequest;

    }


}
