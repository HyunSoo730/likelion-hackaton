package com.example.lionproject.repository;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PublicServiceReservationRepository extends JpaRepository<PublicServiceReservation, Long> {

    @Query("SELECT p.serviceId FROM PublicServiceReservation p")
    List<String> findAllServiceId();

    @Query("SELECT DISTINCT p.serviceId FROM PublicServiceReservation p")
    Set<String> findAllDistinctServiceId();

    List<PublicServiceReservation> findByAreaNMOrReserveTypeOrMaxClassNMOrMinClassNMOrSvcStatNMOrPayAtNMOrUseTgtInfo(
            String areaNM, String reserveType, String maxClassNM, String minClassNM, String svcStatNM, String payAtNM, String useTgtInfo
    ); //페이징 처리 해주기.
}
