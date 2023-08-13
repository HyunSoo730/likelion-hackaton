package com.example.lionproject.repository.employment;

import com.example.lionproject.domain.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface EmploymentRepository extends JpaRepository<Employment, Long>, EmploymentRepositoryCustom {

    @Query("SELECT DISTINCT e.subject FROM Employment e")
    Set<String> findAllDistinctSubject();

    List<Employment> findAllByOrderByApplicationEndDateAsc();

    List<Employment> findByRegistCostAndApplyState(String registCost, String applyState);

    List<Employment> findByRegistCost(String registCost);

    List<Employment> findByApplyState(String applyState);
    List<Employment> findBySubjectContaining(String subject);

}
