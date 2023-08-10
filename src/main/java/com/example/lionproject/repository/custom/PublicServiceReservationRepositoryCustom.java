package com.example.lionproject.repository.custom;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicServiceReservationRepositoryCustom {

    List<PublicServiceReservation> findByFiltered(List<String> areaNM, List<String> reserveType, List<String> maxClassNM,
                                                  List<String> minClassNM, List<String> svcStatNM, List<String> payAtNM);

}
