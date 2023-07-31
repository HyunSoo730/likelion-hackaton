package com.example.lionproject.Batch.Tasklet;

import com.example.lionproject.domain.seoulApi.PublicReservationLastUpdated;
import com.example.lionproject.domain.seoulApi.enums.PublicReservationType;
import com.example.lionproject.repository.seoulApi.PublicReservationLastUpdatedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchLastUpdatedTasklet implements Tasklet {

    private final PublicReservationLastUpdatedRepository repository;
    private boolean updatedToday = false;
    private LocalDate today = LocalDate.now();

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        PublicReservationLastUpdated publicReservationLastUpdated = repository.findFirstByPublicReservationTypeOrderByLastUpdatedDesc(PublicReservationType.GENERAL)
                .orElseGet(() -> PublicReservationLastUpdated.of(0L, PublicReservationType.GENERAL, LocalDate.MIN));
        LocalDate lastUpdated = publicReservationLastUpdated.getLastUpdated();

        if(lastUpdated.equals(today)){
            log.info("[FetchLastUpdatedTasklet] [lastUpdated == today] Already updated for today.");
            contribution.setExitStatus(ExitStatus.COMPLETED);
            return RepeatStatus.FINISHED;
        }
        if(updatedToday){
            log.info("[FetchLastUpdatedTasklet] [updatedToday == true] Already updated for today.");
            repository.saveAndFlush(PublicReservationLastUpdated.of(PublicReservationType.GENERAL, today));

            contribution.setExitStatus(ExitStatus.COMPLETED);
            return RepeatStatus.FINISHED;
        }

        updatedToday = true;
        contribution.setExitStatus(new ExitStatus("CONTINUABLE"));

        return RepeatStatus.FINISHED;
    }
}
