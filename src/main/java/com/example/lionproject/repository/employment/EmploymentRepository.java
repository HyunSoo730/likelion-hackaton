package com.example.lionproject.repository.employment;

import com.example.lionproject.domain.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
}
