package com.example.lionproject.controller.openapi.publicservice;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import com.example.lionproject.repository.PublicServiceReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PublicServiceReservationController {

    private final PublicServiceReservationRepository repository;

    @PostMapping("/public_service")
    public ResponseEntity<List<PublicServiceReservationResponse>> returnEntity2(@RequestBody PublicServiceReservationRequest dto) {
        //해당 dto로 찾아오기
        List<PublicServiceReservation> res = repository.findByAreaNMOrReserveTypeOrMaxClassNMOrMinClassNMOrSvcStatNMOrPayAtNMOrUseTgtInfo(
                dto.getAreaNM(), dto.getReserveType(), dto.getMaxClassNM(),
                dto.getMinClassNM(), dto.getSvcStatNM(), dto.getPayAtNM(), dto.getUseTgtInfo());

        // 조회 결과를 PublicServiceReservationVO의 리스트로 변환하여 반환
        List<PublicServiceReservationResponse> result = res.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * method to convert PublicServiceReservation to PublicServiceReservationVO
     */
    private PublicServiceReservationResponse convertToVO(PublicServiceReservation reservation) {
        PublicServiceReservationResponse vo = new PublicServiceReservationResponse();
        vo.setAreaNM(reservation.getAreaNM());
        vo.setReserveType(reservation.getReserveType());
        vo.setMaxClassNM(reservation.getMaxClassNM());
        vo.setMinClassNM(reservation.getMinClassNM());
        vo.setSvcStatNM(reservation.getSvcStatNM());
        vo.setPayAtNM(reservation.getPayAtNM());
        vo.setUseTgtInfo(reservation.getUseTgtInfo());
        return vo;
    }


}
