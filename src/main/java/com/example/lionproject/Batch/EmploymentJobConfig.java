package com.example.lionproject.Batch;

import com.example.lionproject.domain.dto.EmploymentJsonDto;
import com.example.lionproject.domain.entity.Employment;
import com.example.lionproject.repository.employment.EmploymentRepository;
import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class EmploymentJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final WebClientService webClientService;
    private final EmploymentRepository repository;



    @Bean
    public Job employmentInfoJob(Step employmentInsertStep) {
        log.info("start job [EmploymentInfoJob]");

        return new JobBuilder("employmentInfoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(employmentInsertStep)
                .build();

    }

    @Bean
    @JobScope
    public Step employmentInsertStep(ItemReader<EmploymentJsonDto> employmentReader, ItemProcessor<EmploymentJsonDto, List<Employment>> employmentProcessor,
                                    ItemWriter<List<Employment>> employmentWriter) {
        return new StepBuilder("employmentInsertStep", jobRepository)
                .<EmploymentJsonDto, List<Employment>>chunk(10, platformTransactionManager)
                .reader(employmentReader)
                .processor(employmentProcessor)
                .writer(employmentWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<EmploymentJsonDto> employmentReader() {
        return new ItemReader<EmploymentJsonDto>() {
            private static int CHUNK_SIZE = 10;
            private static int startIndex = 1;
            private static int lastIndex = -1;
            boolean IS_END = false;
            @Override
            public EmploymentJsonDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if (IS_END)
                    return null;

                EmploymentJsonDto employmentJsonDto = webClientService.returnEmploymentDto(startIndex, startIndex + CHUNK_SIZE - 1);
                if (lastIndex == -1) {
                    lastIndex = employmentJsonDto.fetchListTotalCount();
                    log.info("lastIndex = {}", lastIndex);
                }

                startIndex += CHUNK_SIZE;
                if (startIndex > lastIndex) {
                    IS_END = true;
                }
                return employmentJsonDto;
            }
        };
    }

    @Bean
    @StepScope
    public ItemProcessor<EmploymentJsonDto, List<Employment>> employmentProcessor() {
        return new ItemProcessor<EmploymentJsonDto, List<Employment>>() {
            @Override
            public List<Employment> process(EmploymentJsonDto item) throws Exception {
                Set<String> allDistinctSubject = repository.findAllDistinctSubject();  //나중에 또 돌릴때도 중복 제거를 위해
                List<Employment> res = item.toDto().stream()
                        .filter(i -> !allDistinctSubject.contains(i.getSubject()))
                        .map(Employment::fromDto)
                        .collect(Collectors.toList());
                return res;
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<List<Employment>> employmentWriter() {
        return new ItemWriter<List<Employment>>() {
            @Override
            public void write(Chunk<? extends List<Employment>> chunk) throws Exception {
//                log.info("item write is good {}", chunk);
                chunk.forEach(i -> repository.saveAllAndFlush(i));
                log.info("save !!");
            }
        };
    }
}
