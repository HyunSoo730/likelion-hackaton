package com.example.lionproject.repository;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PublicServiceReservationRepository extends JpaRepository<PublicServiceReservation, Long> {

    @Query("SELECT p.serviceId FROM PublicServiceReservation p")
    List<String> findAllServiceId();

    @Query("SELECT DISTINCT p.serviceId FROM PublicServiceReservation p")
    Set<String> findAllDistinctServiceId();

    Page<PublicServiceReservation> findByAreaNMOrReserveTypeOrMaxClassNMOrMinClassNMOrSvcStatNMOrPayAtNMOrderByRcptenddtAsc(
            String areaNM, String reserveType, String maxClassNM, String minClassNM, String svcStatNM, String payAtNM, Pageable pageable
    ); //페이징 처리 + 가가운 마감일자로 정렬

    Page<PublicServiceReservation> findAll(Pageable pageable);
}
