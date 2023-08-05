package com.example.lionproject.controller.openapi.publicservice;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import com.example.lionproject.repository.PublicServiceReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());
        //정렬 & 페이징
        Page<PublicServiceReservation> res = repository.findByAreaNMOrReserveTypeOrMaxClassNMOrMinClassNMOrSvcStatNMOrPayAtNMOrderByRcptenddtAsc(
                dto.getAreaNM(), dto.getReserveType(), dto.getMaxClassNM(),
                dto.getMinClassNM(), dto.getSvcStatNM(), dto.getPayAtNM(),
                pageable);

        // 조회 결과를 PublicServiceReservationVO의 리스트로 변환하여 반환
        List<PublicServiceReservationResponse> result = res.getContent().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        // Page 객체에는 페이징 정보가 포함되어 있으므로 헤더에 페이징 정보를 추가하여 클라이언트에게 전달
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Elements", String.valueOf(res.getTotalElements()));
        headers.add("Total-Pages", String.valueOf(res.getTotalPages()));
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }


    /**
     * method to convert PublicServiceReservation to PublicServiceReservationVO
     */
    private PublicServiceReservationResponse convertToVO(PublicServiceReservation reservation) {
        PublicServiceReservationResponse vo = new PublicServiceReservationResponse();
        vo.setAreaNM(reservation.getAreaNM());
        vo.setMaxClassNM(reservation.getMaxClassNM());
        vo.setSvcNM(reservation.getSvcNM());
        vo.setSvcStatNM(reservation.getSvcStatNM());
        vo.setPayAtNM(reservation.getPayAtNM());
        vo.setPlaceNM(reservation.getPlaceNM());
        vo.setImgUrl(reservation.getImgUrl());
        vo.setSvcUrl(reservation.getSvcUrl());
        vo.setRcptenddt(reservation.getRcptenddt());
        return vo;
    }


}
