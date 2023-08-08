package com.example.lionproject.controller.openapi.employeement;

import com.example.lionproject.domain.dto.EmploymentJsonDto;
import com.example.lionproject.domain.entity.Employment;
import com.example.lionproject.repository.employment.EmploymentRepository;
import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/employment")
public class EmploymentController {

    private final WebClientService webClientService;
    private final EmploymentRepository repository;

    @GetMapping("/employment/test")
    public String returnEntity(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        String res = webClientService.employmentSupport(startIndex, endIndex);
        return res;
    }

    @GetMapping("/employment")
    public ResponseEntity<EmploymentJsonDto> returnDto(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        EmploymentJsonDto employmentDto = webClientService.returnEmploymentDto(startIndex, endIndex);
        return new ResponseEntity<>(employmentDto, HttpStatus.OK);
    }

    /**
     * 전체 데이터 반환
     */
    @GetMapping("/data")
    public ResponseEntity<List<EmploymentResponse>> returnAllData() {
        List<Employment> temp = repository.findAllByOrderByApplicationEndDateAsc();
        List<EmploymentResponse> res = temp.stream().map(this::convertToEmploymentResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
//        log.info("res size : {}", res.size());

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    private EmploymentResponse convertToEmploymentResponse(Employment dto) {

        LocalDate today = LocalDate.now(); // 오늘
        LocalDate applicationEndDate = LocalDate.parse(dto.getApplicationEndDate()); // applicationEndDate가 "yyyy-MM-dd" 형식

        if (applicationEndDate.isBefore(today)) {
            return null;
        } //지난 applicationEndDate의 경우 null 반환

        EmploymentResponse vo = new EmploymentResponse();
        vo.setSubject(dto.getSubject());
        vo.setStartDate(dto.getStartDate());
        vo.setEndDate(dto.getEndDate());
        vo.setApplicationStartDate(dto.getApplicationStartDate());
        vo.setApplicationEndDate(dto.getApplicationEndDate());
        vo.setRegistCost(dto.getRegistCost());
        vo.setRegistPeople(dto.getRegistPeople());
        vo.setApplyState(dto.getApplyState());
        vo.setViewDetail(dto.getViewDetail());
        return vo;
    }
}
