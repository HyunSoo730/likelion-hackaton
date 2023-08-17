package com.example.lionproject.Batch.Tasklet;

import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.repository.kakao.KakaoMemberFavRepository;
import com.example.lionproject.repository.kakao.KakaoRepository;
import com.example.lionproject.repository.senuri.SenuriServiceDetailRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchAllKakaoMembersTasklet implements Tasklet {

    private final KakaoRepository repository;
    private String MEMBERS = "members";

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext executionContext = getExecutionContext(chunkContext);

        // 객체(KakaoMember)를 그대로 Execution Context에 넣으면 java.lang.IllegalArgumentException: Could not serialize the execution contex 에러 발생
        List<Long> members = repository.findAllUserId();
        executionContext.put(MEMBERS, members);

        contribution.setExitStatus(ExitStatus.COMPLETED);
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
}
