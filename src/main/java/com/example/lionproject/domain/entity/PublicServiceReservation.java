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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PublicServiceReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reserveType;    //예약구분

    private String service;//서비스구분코드

    private String gubun;//서비스구분

    private String serviceId;//서비스ID

    private String maxClassNM;//대분류명

    private String minClassNM;//소분류명

    private String svcStatNM;//서비스상태

    private String svcNM;//서비스명

    private String payAtNM;//결제방법

    private String placeNM;//장소명

    private String useTgtInfo;//서비스대상

    private String svcUrl;//바로가기URL

    private String x;//장소x좌표

    private String Y;//장소Y좌표

    private String rcptbgndt;//접수시작일지

    private String rcptenddt;//접수종료일시

    private String areaNM;//지역명

    private String imgUrl;//이미지주소

    private String dtlCont;//상세내용

    private String telNo;//연락처

    private String vMin;//서비스이용시작시간

    private String vMax;//서비스이용종료시간

}
