package com.example.lionproject.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
