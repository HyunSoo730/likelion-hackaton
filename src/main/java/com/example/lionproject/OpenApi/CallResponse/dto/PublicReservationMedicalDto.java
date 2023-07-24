package com.example.lionproject.OpenApi.CallResponse.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PublicReservationMedicalDto {

    private String gubun;
    private String svcId;
    private String maxClassNM;
    private String minClassNM;
    private String svcStatNM;
    private String svnNM;
    private String payatNM;
    private String placeNM;
    private String useTgtInfo;
    private String svcUrl;
    private String x;
    private String y;
    private String svccopnbgndt;
    private String svcopnenddt;
    private String rcptbgndt;
    private String rcptenddt;
    private String areaNM;
    private String imgUrl;
    private String dtlcont;
    private String telno;
    private String vMin;
    private String vMax;
    private String revStdDayNM;
    private String revStdDay;

    public static PublicReservationMedicalDto of(String gubun, String svcId, String maxClassNM, String minClassNM, String svcStatNM, String svnNM, String payatNM,
                                                 String placeNM, String useTgtInfo, String svcUrl, String x, String y, String svccopnbgndt, String svcopnenddt,
                                                 String rcptbgndt, String rcptenddt, String areaNM, String imgUrl, String dtlcont, String telno,
                                                 String vMin, String vMax, String revStdDayNM, String revStdDay) {
        return new PublicReservationMedicalDto(
                gubun,
                svcId,
                maxClassNM,
                minClassNM,
                svcStatNM,
                svnNM,
                payatNM,
                placeNM,
                useTgtInfo,
                svcUrl,
                x,
                y,
                svccopnbgndt,
                svcopnenddt,
                rcptbgndt,
                rcptenddt,
                areaNM,
                imgUrl,
                dtlcont,
                telno,
                vMin,
                vMax,
                revStdDayNM,
                revStdDay
        );
    }
}
