package com.example.lionproject.controller.openapi.publicservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicServiceReservationRequest {
    private String areaNM;//지역명

    private String reserveType;    //예약구분

    private String maxClassNM;//대분류명

    private String minClassNM;//소분류명

    private String svcStatNM;//서비스상태

    private String payAtNM;//결제방법

    //    private String useTgtInfo;//서비스대상  //일단 안씀

//    //페이징 정보 추가
//    private Integer page;
//    private Integer size;  //페이지 당 개수
}
