package com.example.lionproject.controller.openapi.employeement;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentRequest {

    private String registCost;  //교육 비용 -> 무료 / 유료
    private String applyState;  //교육 상태 -> 마감 / 접수중
    private String subject;  //교육명을 검색어로
}
