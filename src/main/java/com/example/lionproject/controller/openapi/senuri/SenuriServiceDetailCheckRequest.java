package com.example.lionproject.controller.openapi.senuri;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SenuriServiceDetailCheckRequest {

    private String etcItm;//기타사항 or 우대사항
    private String frAcptDd;//시작접수일
}
