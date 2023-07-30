package com.example.lionproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString(of = {"serviceId"})
@NoArgsConstructor
@AllArgsConstructor
public class WebClientDTO {

    @JsonProperty("tvYeyakCOllect")
    private PublicServiceReservation publicServiceReservation;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class PublicServiceReservation {
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

        @JsonProperty("DIV")
        private String div;

        @JsonProperty("SERVICE")
        private String service;

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

    public int fetchListTotalCount() {
        return Integer.parseInt(this.publicServiceReservation.listTotalCount);
    }

    public List<PublicServiceReservationDto> toDto() {
        return this.publicServiceReservation.rows.stream()
                .map(r -> PublicServiceReservationDto.of(
                        r.div, r.service, r.gubun, r.serviceId, r.maxClassNM, r.minClassNM, r.svcStatNM, r.svcNM, r.payAtNM, r.placeNM, r.useTgtInfo, "r.svcUrl",
                        r.x, r.y, r.svcOpnBgnDt, r.svcOpnEndDt, r.rcptbgndt, r.rcptenddt, r.areaNM, "r.imgUrl", "r.dtlCont", r.telNo, r.vMin, r.vMax, r.revStdDayNM, r.RevStdDay
                )).collect(Collectors.toList());
    }

}
