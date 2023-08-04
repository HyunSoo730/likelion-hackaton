package com.example.lionproject.controller.openapi.publicservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicServiceReservationResponse {

    private String areaNM;//지역명

    private String reserveType;    //예약구분

    private String gubun;//서비스구분

    private String maxClassNM;//대분류명

    private String minClassNM;//소분류명

    private String svcStatNM;//서비스상태

    private String svcNM;//서비스명

    private String payAtNM;//결제방법

    private String placeNM;//장소명

    private String useTgtInfo;//서비스대상

    private String svcUrl;//바로가기URL

    private String x;//장소x좌표

    private String y;//장소Y좌표

    private String rcptbgndt;//접수시작일지

    private String rcptenddt;//접수종료일시

    private String imgUrl;//이미지주소

    private String telNo;//연락처

    private String vMin;//서비스이용시작시간

    private String vMax;//서비스이용종료시간

}
