package com.example.lionproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebClientDTO {

    @JsonProperty("ListPublicReservationCulture")
    private ListPublicReservationCulture listPublicReservationCulture;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ListPublicReservationCulture {
        @JsonProperty("list_total_count")
        private String listTotalCount;

        @JsonProperty("RESULT")
        private Result result;

        @JsonProperty("row")
        private List<Row> rows;
    }
    //키 값 제대로 보고 파싱해야함.!!!! -> 코드 정리

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
    private static class Row{
        @JsonProperty("GUBUN")
        private String gubun;

        @JsonProperty("SVCID")
        private String serviceId;

        @JsonProperty("MAXCLASSNM")
        private String maxClassNM;

        @JsonProperty("MINCLASSNM")
        private String minClassNM;

        @JsonProperty("SVCSTATNM")
        private String svcStatNM;

        @JsonProperty("SVCNM")
        private String svcNM;

        @JsonProperty("PAYATNM")
        private String payAtNM;

        @JsonProperty("PLACENM")
        private String placeNM;

        @JsonProperty("USETGTINFO")
        private String useTgtInfo;

        @JsonProperty("SVCURL")
        private String svcUrl;

        @JsonProperty("X")
        private String x;

        @JsonProperty("Y")
        private String Y;

        @JsonProperty("SVCOPNBGNDT")
        private String svcOpnBgnDt;

        @JsonProperty("SVCOPNENDDT")
        private String svcOpnEndDt;

        @JsonProperty("RCPTBGNDT")
        private String rcptbgndt;

        @JsonProperty("RCPTENDDT")
        private String rcptenddt;

        @JsonProperty("AREANM")
        private String areaNM;

        @JsonProperty("IMGURL")
        private String imgUrl;

        @JsonProperty("DTLCONT")
        private String dtlCont;

        @JsonProperty("TELNO")
        private String telNo;

        @JsonProperty("V_MIN")
        private String vMin;

        @JsonProperty("V_MAX")
        private String vMax;

        @JsonProperty("REVSTDDAYNM")
        private String revStdDayNM;

        @JsonProperty("REVSTDDAY")
        private String RevStdDay;
    }



}
