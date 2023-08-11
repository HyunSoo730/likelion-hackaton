package com.example.lionproject.Batch.Tasklet;

import com.example.lionproject.OpenApi.CallOpenApi;
import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceRawResponse;
import com.example.lionproject.domain.seoulApi.PublicReservationLastUpdated;
import com.example.lionproject.domain.seoulApi.enums.PublicReservationType;
import com.example.lionproject.repository.seoulApi.PublicReservationLastUpdatedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchSenuriServiceTasklet implements Tasklet {

    private final PublicReservationLastUpdatedRepository repository;
    private final CallOpenApi callOpenApi;
    private String JOB_ID_LIST = "job_id_list";
    private String JOB_ID = "job_id";
    private String PAGE_NO = "page_no";
    private String ITEM_COUNT = "item_count";

    private List<String> ids = new ArrayList<>();
    private int itemCount = 0;

    private static int pageNo = 1;
    private int NUM_OF_ROWS = 10;
    private LocalDate today = LocalDate.now();
    private LocalDate lastUpdated;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext executionContext = getExecutionContext(chunkContext);

        initIds(executionContext);
        initItemCount(executionContext);

        if(itemCount < 0){
            contribution.setExitStatus(ExitStatus.COMPLETED);
            return RepeatStatus.FINISHED;
        }

        String jobId = ids.get(itemCount);
        itemCount--;
        executionContext.putInt(ITEM_COUNT, itemCount);
        executionContext.putString(JOB_ID, jobId);

        contribution.setExitStatus(new ExitStatus("CONTINUABLE"));
        return RepeatStatus.FINISHED;
    }

    private ExecutionContext getExecutionContext(ChunkContext chunkContext) {
        /**
         * ExecutionContext 를 가져오기 위해 먼저 현재 Step 의 StepExecution  가져오기
         */
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();

        /**
         * 현재 Job 에서의 ExecutionContext 를 가져오기
         * -> ExecutionContext 를 통해서 Step 끼리 데이터를 주고 받음
         */
        return stepExecution.getJobExecution().getExecutionContext();
    }

    private void initIds(ExecutionContext executionContext) {
        if(executionContext.containsKey(JOB_ID_LIST)){
            ids = (List<String>) executionContext.get(JOB_ID_LIST);
        }else{
            if (pageNo == 1) {
                lastUpdated = repository.findFirstByPublicReservationTypeOrderByLastUpdatedDesc(PublicReservationType.SENURI)
                        .orElseGet(() -> PublicReservationLastUpdated.of(PublicReservationType.SENURI, today.minusDays(3)))
                        .getLastUpdated();
            }

            while(true){
                List<String> currentList = callOpenApi.CallSenuriService(NUM_OF_ROWS, pageNo).toListDto().stream()
                        .filter(dto -> {
                            String dtoDate = dto.getFrDd();
                            String year = dtoDate.substring(0, 4);
                            String month = dtoDate.substring(4, 6);
                            String day = dtoDate.substring(6, 8);

                            return !LocalDate.parse(year + "-" + month + "-" + day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(lastUpdated);
                        })
                        .map(dto -> dto.getJobId())
                        .collect(Collectors.toList());
                ids.addAll(currentList);
                pageNo++;
                if(currentList.size() < NUM_OF_ROWS){
                    break;
                }
            }

            executionContext.put(JOB_ID_LIST, ids);
        }
    }

    public void initItemCount(ExecutionContext executionContext) {
        if(executionContext.containsKey(ITEM_COUNT)){
            itemCount = executionContext.getInt(ITEM_COUNT);
        }else{
            itemCount = ids.size() - 1;
            executionContext.putInt(ITEM_COUNT, itemCount);
        }

    }


}
