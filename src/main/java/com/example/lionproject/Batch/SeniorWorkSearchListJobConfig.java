package com.example.lionproject.Batch;

import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceRawResponse;
import com.example.lionproject.domain.entity.SenuriServiceList;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class SeniorWorkSearchListJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final WebClientService webClientService;
    private final SenuriServiceListRepository repository;

    @Bean
    public Job senuriWorkSearchListJob(Step senuriWorkSearchListStep) {
        log.info("senuriWorkSearchList Job is started");

        return new JobBuilder("senuriWorkSearchListJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(senuriWorkSearchListStep)
                .build();
    }

    @Bean
    @JobScope
    public Step senuriWorkSearchListStep(ItemReader<SenuriServiceRawResponse> senuriServiceItemReader,
         ItemProcessor<SenuriServiceRawResponse, List<SenuriServiceList>> senuriServiceItemProcessor,
         ItemWriter<List<SenuriServiceList>> senuriServiceItemWriter) {
        return new StepBuilder("senuriServiceStep", jobRepository)
                .<SenuriServiceRawResponse, List<SenuriServiceList>>chunk(1, platformTransactionManager)
                .reader(senuriServiceItemReader)
                .processor(senuriServiceItemProcessor)
                .writer(senuriServiceItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<SenuriServiceRawResponse> senuriServiceItemReader() {
        return new ItemReader<SenuriServiceRawResponse>() {
//            private static int CHUNK_SIZE = 10;
            private static int pageNo = 1;
            private LocalDate today = LocalDate.now();
            private boolean IS_END = false;

            @Override
            public SenuriServiceRawResponse read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if (IS_END)
                    return null;

                SenuriServiceRawResponse response = webClientService.CallSenuriListService(10, pageNo);
                log.info("response = {}", response.toString());
                //                if (response.returnToDd().isBefore(today)) {
//                    log.info("END");
//                    IS_END = true;
//                }
                if (pageNo == 100) {
                    log.info("read END");
                    IS_END = true;
                }
                pageNo += 1;
                return response;
            }
        };
    }

    @Bean
    @StepScope
    public ItemProcessor<SenuriServiceRawResponse, List<SenuriServiceList>> senuriServiceItemProcessor() {
        return new ItemProcessor<SenuriServiceRawResponse, List<SenuriServiceList>>() {
            @Override
            public List<SenuriServiceList> process(SenuriServiceRawResponse item) throws Exception {
                List<SenuriServiceList> res = item.toListDto().stream()
                        .map(SenuriServiceList::fromDto)
                        .collect(Collectors.toList());
                return res;
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<List<SenuriServiceList>> senuriServiceItemWriter() {
        return items -> items.forEach(i -> repository.saveAllAndFlush(i));
    }
}
