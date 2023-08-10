package com.example.lionproject.repository;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import com.example.lionproject.repository.custom.PublicServiceReservationRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PublicServiceReservationRepository extends JpaRepository<PublicServiceReservation, Long>, PublicServiceReservationRepositoryCustom {

    @Query("SELECT p.serviceId FROM PublicServiceReservation p")
    List<String> findAllServiceId();

    @Query("SELECT DISTINCT p.serviceId FROM PublicServiceReservation p")
    Set<String> findAllDistinctServiceId();

    //페이징 처리 + 가가운 마감일자로 정렬
    Page<PublicServiceReservation> findByAreaNMOrReserveTypeOrMaxClassNMOrMinClassNMOrSvcStatNMOrPayAtNMOrderByRcptenddtAsc(
            String areaNM, String reserveType, String maxClassNM, String minClassNM, String svcStatNM, String payAtNM,
            Pageable pageable
    );

    List<PublicServiceReservation> findByAreaNMOrReserveTypeOrMaxClassNMOrMinClassNMOrSvcStatNMOrPayAtNMOrderByRcptenddtAsc(
            String areaNM, String reserveType, String maxClassNM, String minClassNM, String svcStatNM, String payAtNM
    );

    List<PublicServiceReservation> findByAreaNMInAndReserveTypeInAndMaxClassNMInAndMinClassNMInAndSvcStatNMInAndPayAtNMInOrderByRcptenddtAsc(
            List<String> areaNM, List<String> reserveType, List<String> maxClassNM,
            List<String> minClassNM, List<String> svcStatNM, List<String> payAtNM
    );
    List<PublicServiceReservation> findBySvcNMContainingOrderByRcptenddtAsc(String serviceName);

    List<PublicServiceReservation> findAllByOrderByRcptenddtAsc();

    Page<PublicServiceReservation> findAll(Pageable pageable);

    // SELECT *FROM PublicServiceReservation WHERE areaNM in :areaNM;
    Page<PublicServiceReservation> findByAreaNMInOrderByRcptenddt(List<String> areaNM, Pageable pageable);

}
