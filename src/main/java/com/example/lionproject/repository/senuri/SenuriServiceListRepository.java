package com.example.lionproject.repository.senuri;

import com.example.lionproject.domain.entity.SenuriServiceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SenuriServiceListRepository extends JpaRepository<SenuriServiceList, Long> {

    @Query("SELECT DISTINCT s.jobId FROM SenuriServiceList s")
    Set<String> findAllDistinctJobId();   //JobId 중복 확인용

    @Query("SELECT s.jobId FROM SenuriServiceList s")
    List<String> findAllJobId();


    SenuriServiceList findFirstByJobId(String jobId);

}
