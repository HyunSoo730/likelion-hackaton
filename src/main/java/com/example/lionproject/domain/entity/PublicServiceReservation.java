package com.example.lionproject.domain.entity;

import com.example.lionproject.domain.dto.PublicServiceReservationDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@ToString(of = {"serviceId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PublicServiceReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reserveType;    //예약구분

    private String service;//서비스구분코드

    private String gubun;//서비스구분

    private String serviceId;//서비스ID

    private String maxClassNM;//대분류명

    private String minClassNM;//소분류명

    private String svcStatNM;//서비스상태

    private String svcNM;//서비스명

    private String payAtNM;//결제방법

    private String placeNM;//장소명

    private String useTgtInfo;//서비스대상

    private String svcUrl;//바로가기URL

    private String x;//장소x좌표

    private String y;//장소Y좌표

    private String rcptbgndt;//접수시작일지

    private String rcptenddt;//접수종료일시

    private String areaNM;//지역명

    private String imgUrl;//이미지주소

    private String dtlCont;//상세내용

    private String telNo;//연락처

    private String vMin;//서비스이용시작시간

    private String vMax;//서비스이용종료시간

    public static PublicServiceReservation of(String reserveType, String service, String gubun, String serviceId, String maxClassNM, String minClassNM,
                                              String svcStatNM, String svcNM, String payAtNM, String placeNM, String useTgtInfo, String svcUrl,
                                              String x, String y, String rcptbgndt, String rcptenddt, String areaNM, String imgUrl, String dtlCont, String telNo, String vMin, String vMax) {
        return new PublicServiceReservation(
                null,
                reserveType,
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
                rcptbgndt,
                rcptenddt,
                areaNM,
                imgUrl,
                dtlCont,
                telNo,
                vMin,
                vMax
        );
    }

    public static PublicServiceReservation of(String serviceId) {
        return PublicServiceReservation.of(
                null,
                null,
                null,
                serviceId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static PublicServiceReservation fromDto(PublicServiceReservationDto dto) {
        return PublicServiceReservation.of(
                dto.getDiv(),
                dto.getService(),
                dto.getGubun(),
                dto.getServiceId(),
                dto.getMaxClassNM(),
                dto.getMinClassNM(),
                dto.getSvcStatNM(),
                dto.getSvcNM(),
                dto.getPayAtNM(),
                dto.getPlaceNM(),
                dto.getUseTgtInfo(),
                dto.getSvcUrl(),
                dto.getX(),
                dto.getY(),
                dto.getRcptbgndt(),
                dto.getRcptenddt(),
                dto.getAreaNM(),
                dto.getImgUrl(),
                dto.getDtlCont(),
                dto.getTelNo(),
                dto.getVMin(),
                dto.getVMax()
        );
    }
}
