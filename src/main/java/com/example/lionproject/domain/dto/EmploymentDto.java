package com.example.lionproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDto {

    private String idx;  //교육 넘버
    private String subject; //교육명
    private String startDate; //교육 시작일
    private String endDate; //교육 종료일
    private String applicationStartDate;  // 교육 신청 시작일
    private String applicationEndDate;  //교육 신청 종료일
    private String registPeople;  //수강 정원
    private String registCost;  //교육 비용
    private String applyState;  //교육 상태
    private String viewDetail;  //강좌상세화면

//    public EmploymentDto(String idx, String subject, String startDate, String endDate, String applicationStartDate,
//                         String applicationEndDate, String registPeople, String registCost, String applyState, String viewDetail) {
//        this.idx = idx;
//        this.subject = subject;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.applicationStartDate = applicationStartDate;
//        this.applicationEndDate = applicationEndDate;
//        this.registPeople = registPeople;
//        this.registCost = registCost;
//        this.applyState = applyState;
//        this.viewDetail = viewDetail;
//    }
}
