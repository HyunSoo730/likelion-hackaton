package com.example.lionproject.Batch;

import com.example.lionproject.Batch.Reader.SenuriServiceDetailReader;
import com.example.lionproject.OpenApi.CallOpenApi;
import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceDetailRawResponse;
import com.example.lionproject.domain.entity.SenuriServiceDetail;
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
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SenuriSeviceInsertJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final CallOpenApi callOpenApi;

    @Bean
    public Job senuriServiceInsertJob(Step fetchSenuriServiceStep, Step fetchSenuriServiceDetailStep) {
        log.info("[SenuriServiceInsertJob] Job Launched");

        return new JobBuilder("senuriServiceInsertJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(fetchSenuriServiceStep)
                    .on("CONTINUABLE").to(fetchSenuriServiceDetailStep).next(fetchSenuriServiceStep)
                    .from(fetchSenuriServiceStep).on("*").end()
                .end()
                .build();
    }

    @Bean
    @JobScope
    public Step fetchSenuriServiceStep(Tasklet fetchSenuriServiceTasklet) {
        return new StepBuilder("fetchSenuriServiceStep", jobRepository)
                .allowStartIfComplete(true)
                .tasklet(fetchSenuriServiceTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    @JobScope
    public Step fetchSenuriServiceDetailStep(ItemReader<List<SenuriServiceDetailRawResponse>> senuriServiceReader,
                                             ItemProcessor<List<SenuriServiceDetailRawResponse>, List<List<SenuriServiceDetail>>> senuriServiceProcessor,
                                             ItemWriter<List<List<SenuriServiceDetail>>> senuriServiceWriter) {
        log.info("[SenuriServiceInsertJobConfig] Insert Info Step Launched");
        return new StepBuilder("fetchSenuriServiceDetailStep", jobRepository)
                .allowStartIfComplete(true)
                .<List<SenuriServiceDetailRawResponse>, List<List<SenuriServiceDetail>>>chunk(5, platformTransactionManager)
                .reader(senuriServiceReader)
                .processor(senuriServiceProcessor)
                .writer(senuriServiceWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<List<SenuriServiceDetailRawResponse>> senuriServiceReader(@Value("#{jobExecutionContext['job_id']}") String jobId) {
        log.info("[SenuriServiceReader] jobId: {}", jobId);

        return new SenuriServiceDetailReader(callOpenApi, List.of(jobId));
    }

    @Bean
    @StepScope
    public ItemProcessor<List<SenuriServiceDetailRawResponse>, List<List<SenuriServiceDetail>>> senuriServiceProcessor(){
        return item -> item.stream()
                .map(i -> i.toListDto().stream()
                        .map(SenuriServiceDetail::fromDto)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Bean
    @StepScope
    public ItemWriter<List<List<SenuriServiceDetail>>> senuriServiceWriter() {
        return items -> items.forEach(i -> log.info("[SenuriServiceInsertJobConfig] [Writer] {}, {}", i, i.size()));
    }


}
