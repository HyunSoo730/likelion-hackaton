package com.example.lionproject.domain.dto;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString(of = {"serviceId"})
@NoArgsConstructor
@AllArgsConstructor
public class PublicServiceReservationDto {

    private String div;
    private String service;
    private String gubun;
    private String serviceId;
    private String maxClassNM;
    private String minClassNM;
    private String svcStatNM;
    private String svcNM;
    private String payAtNM;
    private String placeNM;
    private String useTgtInfo;
    private String svcUrl;
    private String x;
    private String y;
    private String svcOpnBgnDt;
    private String svcOpnEndDt;
    private String rcptbgndt;
    private String rcptenddt;
    private String areaNM;
    private String imgUrl;
    private String dtlCont;
    private String telNo;
    private String vMin;
    private String vMax;
    private String revStdDayNM;
    private String revStdDay;

    public static PublicServiceReservationDto of(String div, String service, String gubun, String serviceId, String maxClassNM, String minClassNM, String svcStatNM,
                                                 String svcNM, String payAtNM, String placeNM, String useTgtInfo, String svcUrl, String x, String y,
                                                 String svcOpnBgnDt, String svcOpnEndDt, String rcptbgndt, String rcptenddt, String areaNM, String imgUrl,
                                                 String dtlCont, String telNo, String vMin, String vMax, String revStdDayNM, String revStdDay) {
        return new PublicServiceReservationDto(
                div,
                service,
                gubun,
                serviceId,
                maxClassNM,
                minClassNM,
                svcStatNM,
                svcNM,
                payAtNM,
                placeNM,
                useTgtInfo,
                svcUrl,
                x,
                y,
                svcOpnBgnDt,
                svcOpnEndDt,
                rcptbgndt,
                rcptenddt,
                areaNM,
                imgUrl,
                dtlCont,
                telNo,
                vMin,
                vMax,
                revStdDayNM,
                revStdDay
        );
    }

}
