package com.example.lionproject.controller.openapi.senuri;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SenuriServiceDetailCheckRequest {

    private List<String> area; //지역구
    private List<String> jobPosition;  //채용공고 형태
    private List<String> status;  //공고상태 진행중 / 마감 둘 중 하나
    private String wantedTitle;  //채용 제목 검색어
}
