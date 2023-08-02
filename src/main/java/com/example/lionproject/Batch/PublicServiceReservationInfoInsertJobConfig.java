package com.example.lionproject.Batch;

import com.example.lionproject.Batch.Reader.PublicServiceReservationReader;
import com.example.lionproject.domain.dto.WebClientDTO;
import com.example.lionproject.domain.entity.PublicServiceReservation;
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
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PublicServiceReservationInfoInsertJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final WebClientService webClientService;

    @Bean
    public Job publicServiceReservationInfoInsertJob(Step insertPublicServiceReservationStep) {
        log.info("[PublicServiceReservationInfoInsertJobConfig] Job Launched");

        return new JobBuilder("publicServiceReservationInfoInsertJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(insertPublicServiceReservationStep)
                .build();
    }

    @Bean
    @JobScope
    public Step insertPublicServiceReservationStep(ItemReader<WebClientDTO> publicServiceReservationReader,
                                                   ItemProcessor<WebClientDTO, List<PublicServiceReservation>> publicServiceReservationProcessor,
                                                   ItemWriter<List<PublicServiceReservation>> publicServiceReservationWriter) {
        log.info("[PublicServiceReservationInfoInsertJobConfig] Insert Info Step Launched");

        return new StepBuilder("insertPublicServiceReservationStep", jobRepository)
                .<WebClientDTO, List<PublicServiceReservation>>chunk(10, platformTransactionManager)
                .reader(publicServiceReservationReader)
                .processor(publicServiceReservationProcessor)
                .writer(publicServiceReservationWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<WebClientDTO> publicServiceReservationReader() {
        return new PublicServiceReservationReader(webClientService);
    }

    @Bean
    @StepScope
    public ItemProcessor<WebClientDTO, List<PublicServiceReservation>> publicServiceReservationProcessor(){
        return item -> item.toDto().stream()
                .map(PublicServiceReservation::fromDto)
                .collect(Collectors.toList());
    }

    @Bean
    @StepScope
    public ItemWriter<List<PublicServiceReservation>> publicServiceReservationWriter() {
        return items -> items.forEach(i -> i.forEach(ii -> log.info("[PublicServiceReservationWriter] {}", ii.getServiceId())));
    }


}
