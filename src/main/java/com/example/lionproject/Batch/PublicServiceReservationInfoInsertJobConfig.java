package com.example.lionproject.Batch;

import com.example.lionproject.Batch.Reader.PublicServiceReservationReader;
import com.example.lionproject.domain.dto.WebClientDTO;
import com.example.lionproject.domain.entity.PublicServiceReservation;
import com.example.lionproject.repository.PublicServiceReservationRepository;
import com.example.lionproject.service.Api.WebClientService;
import io.micrometer.common.util.StringUtils;
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
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PublicServiceReservationInfoInsertJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final WebClientService webClientService;
    private final PublicServiceReservationRepository repository;

    @Bean
    public Job publicServiceReservationInfoInsertJob(Step fetchLastUpdated, Step insertPublicServiceReservationStep) {
        log.info("[PublicServiceReservationInfoInsertJobConfig] Job Launched");

        return new JobBuilder("publicServiceReservationInfoInsertJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(fetchLastUpdated).on("CONTINUABLE")
                .to(insertPublicServiceReservationStep)
                .next(fetchLastUpdated)
                .from(fetchLastUpdated).on("COMPLETED")
                .end()
                .end()
                .build();
    }

    @Bean
    @JobScope
    public Step fetchLastUpdated(Tasklet fetchLastUpdatedTasklet) {
        return new StepBuilder("fetchLastUpdated", jobRepository)
                .tasklet(fetchLastUpdatedTasklet, platformTransactionManager)
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
    public ItemProcessor<WebClientDTO, List<PublicServiceReservation>> publicServiceReservationProcessor() {
        Set<String> serviceIds = repository.findAllDistinctServiceId();
        return item -> item.toDto().stream()
                .filter(i -> !serviceIds.contains(i.getServiceId()))
                .map(PublicServiceReservation::fromDto)
                .collect(Collectors.toList());
    }

    @Bean
    @StepScope
    public ItemWriter<List<PublicServiceReservation>> publicServiceReservationWriter() {
        return items -> items.forEach(i -> repository.saveAllAndFlush(i));
    }

}
