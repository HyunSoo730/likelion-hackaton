package com.example.lionproject.controller.openapi.publicservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicServiceReservationResponse {

    private String areaNM;//지역명

    private String maxClassNM;//대분류명

    private String svcStatNM;//서비스상태

    private String svcNM;//서비스명

    private String payAtNM;//결제방법

    private String placeNM;//장소명

    private String svcUrl;//바로가기URL

    private String rcptenddt;//접수종료일시

    private String imgUrl;//이미지주소



}
