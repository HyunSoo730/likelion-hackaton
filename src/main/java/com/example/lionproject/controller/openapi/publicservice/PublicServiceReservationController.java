package com.example.lionproject.controller.openapi.publicservice;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import com.example.lionproject.repository.PublicServiceReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PublicServiceReservationController {

    private final PublicServiceReservationRepository repository;

    /**
     * 검색 필터 정보로 데이터 가져옴
     */
    @PostMapping("/public_service")
    public ResponseEntity<List<PublicServiceReservationResponse>> returnEntity2(@RequestBody PublicServiceReservationRequest dto) {
        //해당 dto로 찾아오기
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());
        //오늘 날짜 가져오기
        LocalDateTime today = LocalDateTime.now();
        // LocalDateTime을 "yyyy-MM-dd HH:mm:ss" 형식의 문자열로 변환합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String todayString = today.format(formatter);

        //정렬 & 페이징
        Page<PublicServiceReservation> res = repository.findByAreaNMOrReserveTypeOrMaxClassNMOrMinClassNMOrSvcStatNMOrPayAtNMOrderByRcptenddtAsc(
               dto.getAreaNM(), dto.getReserveType(), dto.getMaxClassNM(),
                dto.getMinClassNM(), dto.getSvcStatNM(), dto.getPayAtNM(),
                pageable);

        // 조회 결과를 PublicServiceReservationResponse로
//        Page<PublicServiceReservationResponse> result = (Page<PublicServiceReservationResponse>) res
//                .map(this::convertToVO)
//                .stream().filter(Objects::nonNull);// Streamable을 Page로 변환

        List<PublicServiceReservation> reservations = res.getContent();
        List<PublicServiceReservationResponse> result = reservations.stream()
                .map(this::convertToVO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // Page 객체에는 페이징 정보가 포함되어 있으므로 헤더에 페이징 정보를 추가하여 클라이언트에게 전달
        // 결과 리스트에서 null인 요소들을 제거합니다.
        result.removeIf(Objects::isNull);
        for (PublicServiceReservationResponse publicServiceReservationResponse : result) {
            System.out.println("publicServiceReservationResponse = " + publicServiceReservationResponse);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Elements", String.valueOf(res.getTotalElements()));
        headers.add("Total-Pages", String.valueOf(res.getTotalPages()));
        return new ResponseEntity<>(result, headers,  HttpStatus.OK);
    }

    /**
     * 전체 데이터 조회
     */
    @GetMapping("/public_service")
    public ResponseEntity<Page<PublicServiceReservationResponse>> returnAllData(@RequestParam int page, @RequestParam int size) {
        /**
         * 한 페이지 당 6개 데이터
         */
        // 정렬 정보 지정 (rcptenddt 프로퍼티를 오름차순으로 정렬)
        Sort sort = Sort.by(Sort.Direction.ASC, "rcptenddt");  //마감일자로 오름차순 진행
        //Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size, sort);
        //페이징 처리를 위해 pageable 파라미터를 추가하여 쿼리 호출
        Page<PublicServiceReservation> pageResult = repository.findAll(pageable);

        //조회 결과를 Response 객체로 반환
        Page<PublicServiceReservationResponse> res = pageResult.map(this::convertToVO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Elements", String.valueOf(pageResult.getTotalElements()));
        headers.add("Total-Pages", String.valueOf(pageResult.getTotalPages()));

        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }


    /**
     * method to convert PublicServiceReservation to PublicServiceReservationVO
     */
    private PublicServiceReservationResponse convertToVO(PublicServiceReservation reservation) {

        //마감 일자가 null이거나 "" 빈값이면 null 반환
        String rcptenddtStr = reservation.getRcptenddt();
        if (rcptenddtStr == null || rcptenddtStr.isEmpty()) {
            return null;
        }
        //오늘 이전인 경우 null 반환.
        LocalDateTime rcptenddt = LocalDateTime.parse(rcptenddtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        if (rcptenddt.isBefore(LocalDateTime.now())) {
            return null;
        }

        PublicServiceReservationResponse vo = new PublicServiceReservationResponse();
        vo.setAreaNM(reservation.getAreaNM());
        vo.setMaxClassNM(reservation.getMaxClassNM());
        vo.setSvcNM(reservation.getSvcNM());
        vo.setSvcStatNM(reservation.getSvcStatNM());
        vo.setPayAtNM(reservation.getPayAtNM());
        vo.setPlaceNM(reservation.getPlaceNM());
        vo.setImgUrl(reservation.getImgUrl());
        vo.setSvcUrl(reservation.getSvcUrl());
//        vo.setRcptenddt(reservation.getRcptenddt());
        vo.setRcptenddt(rcptenddt);
        return vo;
    }


}
