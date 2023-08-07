package com.example.lionproject.repository.employment;

import com.example.lionproject.domain.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {

    @Query("SELECT DISTINCT e.subject FROM Employment e")
    Set<String> findAllDistinctSubject();
}
