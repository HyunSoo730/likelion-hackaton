package com.example.lionproject.Batch;

import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceDetailRawResponse;
import com.example.lionproject.domain.entity.SenuriServiceDetail;
import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import com.example.lionproject.domain.entity.SenuriServiceList;
import com.example.lionproject.repository.senuri.SenuriServiceDetailRepository;
import com.example.lionproject.repository.senuri.SenuriServiceListRepository;
import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SenuriWorkSearchDetailJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final WebClientService webClientService;
    private final SenuriServiceDetailRepository repository;

    /**
     * 레포지토리를 통해 데이터 읽어오기 위해
     */
    private final SenuriServiceListRepository senuriServiceListRepository;

    @Bean
    public Job senuriWorkSearchDetailJob(Step senuriWorkSearchDetailStep) {
        log.info("senuriWorkSearchDetailJob is started");
        return new JobBuilder("senuriWorkSearchDetailJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(senuriWorkSearchDetailStep)
                .build();
    }

    @JobScope
    @Bean
    public Step senuriWorkSearchDetailStep(ItemReader<SenuriServiceDetailRawResponse> senuriServiceDetailItemReader,
                                           ItemProcessor<SenuriServiceDetailRawResponse, SenuriServiceDetailCheck> senuriServiceDetailItemProcessor,
                                           ItemWriter<SenuriServiceDetailCheck> senuriServiceDetailCheckItemWriter) {
        return new StepBuilder("senuriWorkSearchDetailStep", jobRepository)
                .<SenuriServiceDetailRawResponse, SenuriServiceDetailCheck>chunk(10, platformTransactionManager)
                .reader(senuriServiceDetailItemReader)
                .processor(senuriServiceDetailItemProcessor)
                .writer(senuriServiceDetailCheckItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<SenuriServiceDetailRawResponse> senuriServiceDetailItemReader() {
        return new ItemReader<SenuriServiceDetailRawResponse>() {
            List<String> allJobId = senuriServiceListRepository.findAllJobId();
            private LocalDate today = LocalDate.now();
            private boolean IS_END = false;
            private int currentIndex = 0;  // 인덱스를 사용하여 리스트의 현재 위치를 추적

            @Override
            public SenuriServiceDetailRawResponse read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if(IS_END)
                    return null;

                if (currentIndex < allJobId.size()) {
                    String jobId = allJobId.get(currentIndex);
                    currentIndex++;

                    // CallSenuriDetailService 메서드를 통해 OpenAPI 데이터 가져오기
                    SenuriServiceDetailRawResponse senuriServiceDetailRawResponse = webClientService.CallSenuriDetailService(jobId);
                    return senuriServiceDetailRawResponse;
                } else {
                    // 리스트의 끝에 도달한 경우
                    IS_END = true;
                    return null;
                }
            }
        };
    }

    @Bean
    @StepScope
    public ItemProcessor<SenuriServiceDetailRawResponse, SenuriServiceDetailCheck> senuriServiceDetailItemProcessor() {
        return new ItemProcessor<SenuriServiceDetailRawResponse, SenuriServiceDetailCheck>() {
            @Override
            public SenuriServiceDetailCheck process(SenuriServiceDetailRawResponse item) throws Exception {
                //여기서 변환시켜야함.
                SenuriServiceDetailCheck senuriServiceDetailCheck = item.fromDto();
                return senuriServiceDetailCheck;
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<SenuriServiceDetailCheck> senuriServiceDetailCheckItemWriter() {
        return items -> {
            for (SenuriServiceDetailCheck item : items) {
                // 여기서 item을 데이터베이스에 저장하는 로직을 구현
                repository.saveAndFlush(item);
            }
        };
    }
}
