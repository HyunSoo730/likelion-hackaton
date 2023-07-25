package com.example.lionproject.repository;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicServiceReservationRepository extends JpaRepository<PublicServiceReservation, Long> {

    @Query("SELECT p.serviceId FROM PublicServiceReservation p")
    List<String> findAllServiceId();

}
