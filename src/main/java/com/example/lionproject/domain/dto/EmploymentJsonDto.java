package com.example.lionproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentJsonDto {

    @JsonProperty("tbViewProgram")
    private Employment employeement;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Employment {
        @JsonProperty("list_total_count")
        private String list_total_count;

        @JsonProperty("RESULT")
        private Result result;

        @JsonProperty("row")
        private List<Row> rows;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Result{
        @JsonProperty("CODE")
        private String code;
        @JsonProperty("MESSAGE")
        private String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Row {

        @JsonProperty("IDX")
        private String idx;  //교육 넘버
        @JsonProperty("SUBJECT")
        private String subject; //교육명
        @JsonProperty("STARTDATE")
        private String startDate; //교육 시작일
        @JsonProperty("ENDDATE")
        private String endDate; //교육 종료일
        @JsonProperty("APPLICATIONSTARTDATE")
        private String applicationStartDate;  // 교육 신청 시작일
        @JsonProperty("APPLICATIONENDDATE")
        private String applicationEndDate;  //교육 신청 종료일
        @JsonProperty("REGISTPEOPLE")
        private String registPeople;  //수강 정원
        @JsonProperty("REGISTCOST")
        private String registCost;  //교육 비용
        @JsonProperty("APPLY_STATE")
        private String applyState;  //교육 상태
        @JsonProperty("VIEWDETAIL")
        private String viewDetail;  //강좌상세화면
    }

    public int fetchListTotalCount() {
        return Integer.parseInt(this.employeement.list_total_count);
    }

    public List<EmploymentDto> toDto() {
        List<EmploymentDto> res = employeement.rows.stream()
                .map(r -> new EmploymentDto(
                        r.idx, r.subject, r.startDate, r.endDate, r.applicationStartDate, r.applicationEndDate, r.registPeople, r.registCost, r.applyState, r.viewDetail
                )).collect(Collectors.toList());
        return res;
    }



}
