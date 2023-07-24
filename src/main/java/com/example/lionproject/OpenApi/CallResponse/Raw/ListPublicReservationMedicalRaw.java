package com.example.lionproject.OpenApi.CallResponse.Raw;

import com.example.lionproject.OpenApi.CallResponse.dto.PublicReservationMedicalDto;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ListPublicReservationMedical")
public class ListPublicReservationMedicalRaw {

    @XmlElement(name = "list_total_count")
    private String count;

    @XmlElement(name = "RESULT")
    private Result result;

    @XmlElement(name = "row")
    private List<Row> rows;


    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Result{
        @XmlElement(name = "CODE")
        private String code;

        @XmlElement(name = "MESSAGE")
        private String message;
    }

    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Row{
        @XmlElement(name = "GUBUN")
        private String gubun;

        @XmlElement(name = "SVCID")
        private String svcId;

        @XmlElement(name = "MAXCLASSNM")
        private String maxClassNM;

        @XmlElement(name = "MINCLASSNM")
        private String minClassNM;

        @XmlElement(name = "SVCSTATNM")
        private String svcStatNM;

        @XmlElement(name = "SVCNM")
        private String svnNM;

        @XmlElement(name = "PAYATNM")
        private String payatNM;

        @XmlElement(name = "PLACENM")
        private String placeNM;

        @XmlElement(name = "USETGTINFO")
        private String useTgtInfo;

        @XmlElement(name = "SVCURL")
        private String svcUrl;

        @XmlElement(name = "X")
        private String x;

        @XmlElement(name = "Y")
        private String y;

        @XmlElement(name = "SVCOPNBGNDT")
        private String svccopnbgndt;

        @XmlElement(name = "SVCOPNENDDT")
        private String svcopnenddt;

        @XmlElement(name = "RCPTBGNDT")
        private String rcptbgndt;

        @XmlElement(name = "RCPTENDDT")
        private String rcptenddt;

        @XmlElement(name = "AREANM")
        private String areaNM;

        @XmlElement(name = "IMGURL")
        private String imgUrl;

        @XmlElement(name = "DTLCONT")
        private String dtlcont;

        @XmlElement(name = "TELNO")
        private String telno;

        @XmlElement(name = "V_MIN")
        private String vMin;

        @XmlElement(name = "V_MAX")
        private String vMax;

        @XmlElement(name = "REVSTDDAYNM")
        private String revStdDayNM;

        @XmlElement(name = "REVSTDDAY")
        private String revStdDay;


    }

    public String fetchResultCode(){
        return this.result.code;
    }

    public List<Row> fetchRows(){ // getRows() 로 설정기 오류 발생 -> 1 counts of IllegalAnnotationExceptions
        return this.rows;
    }

    public List<PublicReservationMedicalDto> toDto(){
        return this.fetchRows().stream()
                .map(r -> PublicReservationMedicalDto.of(
                        r.gubun,
                        r.svcId,
                        r.maxClassNM,
                        r.minClassNM,
                        r.svcStatNM,
                        r.svnNM,
                        r.payatNM,
                        r.placeNM,
                        r.useTgtInfo,
                        r.svcUrl,
                        r.x,
                        r.y,
                        r.svccopnbgndt,
                        r.svcopnenddt,
                        r.rcptbgndt,
                        r.rcptenddt,
                        r.areaNM,
                        r.imgUrl,
                        r.dtlcont,
                        r.telno,
                        r.vMin,
                        r.vMax,
                        r.revStdDayNM,
                        r.revStdDay
                )).collect(Collectors.toList());
    }

}
