package com.example.lionproject.controller.openapi.publicservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicServiceReservationRequest {
    private List<String> areaNM;    // 지역명 리스트
    private List<String> reserveType;    // 예약구분 리스트
    private List<String> maxClassNM;    // 대분류명 리스트
    private List<String> minClassNM;    // 소분류명 리스트
    private List<String> svcStatNM;     // 서비스상태 리스트
    private List<String> payAtNM;       // 결제방법 리스트
    private String svcNM; //서비스명

    //    private String useTgtInfo;//서비스대상  //일단 안씀

//    //페이징 정보 추가
//    private Integer page;
//    private Integer size;  //페이지 당 개수
}
