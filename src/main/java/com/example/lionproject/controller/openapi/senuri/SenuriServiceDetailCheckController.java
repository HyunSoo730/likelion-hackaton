package com.example.lionproject.controller.openapi.senuri;

import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import com.example.lionproject.domain.entity.SenuriServiceList;
import com.example.lionproject.repository.senuri.SenuriServiceDetailRepository;
import com.example.lionproject.repository.senuri.SenuriServiceListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/job_search")
public class SenuriServiceDetailCheckController {

    private final SenuriServiceDetailRepository detailRepository;
    private final SenuriServiceListRepository listRepository;

    @GetMapping("/all_data")
    public ResponseEntity<List<SenuriServiceDetailCheckResponse>> returnAllData() {


        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        //전체 데이터를 보여줄 때는 서울시 데이터만 보여주기로
        List<SenuriServiceDetailCheck> allData = detailRepository.findAfterTodayAndCityOrderByToAcptDdAsc(today.format(formatter), "서울");
        List<SenuriServiceDetailCheckResponse> res = allData.stream()
                .filter(data -> LocalDate.parse(data.getToAcptDd(), formatter).isAfter(today))
                .map(this::convertToResponse)
                .limit(150)
                .collect(Collectors.toList());

        log.info("size = {}", res.size());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 검색어 + 체크박스 필터 적용
     */
    @PostMapping("/filter_data")
    public ResponseEntity<List<SenuriServiceDetailCheckResponse>> returnFilterData(@RequestBody SenuriServiceDetailCheckRequest request) {
        List<SenuriServiceDetailCheck> temp = detailRepository.findFiltered(request.getArea(), request.getJobPosition(), request.getStatus(), request.getWantedTitle());
        List<SenuriServiceDetailCheckResponse> res = temp.stream().map(this::convertToResponse)
                .limit(150)
                .collect(Collectors.toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    private SenuriServiceDetailCheckResponse convertToResponse(SenuriServiceDetailCheck detailCheck) {

        SenuriServiceDetailCheckResponse response = new SenuriServiceDetailCheckResponse();
        response.setAcptMthdCd(detailCheck.getAcptMthdCd());
        response.setClerk(detailCheck.getClerk());
        response.setClerkContt(detailCheck.getClerkContt());
        response.setClltPrnnum(detailCheck.getClltPrnnum());
        response.setEtcItm(detailCheck.getEtcItm());
        response.setFrAcptDd(detailCheck.getFrAcptDd());
        response.setPlDetAddr(detailCheck.getPlDetAddr());
        response.setPlbizNm(detailCheck.getPlbizNm());
        response.setToAcptDd(detailCheck.getToAcptDd());
        response.setWantedTitle(detailCheck.getWantedTitle());
        response.setEmplymShpNm(detailCheck.getEmplymShpNm());
        response.setDeadline(detailCheck.getDeadline());
        return response;
    }



}
