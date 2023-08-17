package com.example.lionproject.controller.openapi.senuri;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SenuriServiceDetailCheckResponse {

    private String acptMthdCd; //접수방법
    private String clerk;//담당자
    private String clerkContt;//담당자연락처
    private String clltPrnnum;//채용공고 모집인원
    private String etcItm;//기타사항 or 우대사항
    private String frAcptDd;//시작접수일
    private String plDetAddr;//주소
    private String plbizNm;//사업장명
    private String toAcptDd;//종료접수일
    private String wantedTitle;//채용제목
    private String emplymShpNm;  //채용공고 형태명 ex 기간제 일자리. --> 이건 List 테이블에서 가져와야함.
    private String deadline;  //마감 / 접수중 -> 이것도 List에서 가져와야함.


}
