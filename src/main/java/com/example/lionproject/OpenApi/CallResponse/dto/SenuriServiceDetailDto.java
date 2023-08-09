package com.example.lionproject.OpenApi.CallResponse.dto;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SenuriServiceDetailDto {

    private String acptMthdCd;
    private String age;
    private String clerk;
    private String clerkContt;
    private String clltPrnnum;
    private String createDy;
    private String etcItm;
    private String frAcptDd;
    private String homepage;
    private String jobId;
    private String lnkStmId;
    private String organYn;
    private String plDetAddr;
    private String plbizNm;
    private String repr;
    private String stmId;
    private String toAcptDd;
    private String updDy;
    private String wantedAuthNo;
    private String wantedTitle;

    public static SenuriServiceDetailDto of(String acptMthdCd, String age, String clerk, String clerkContt, String clltPrnnum, String createDy, String etcItm,
                                            String frAcptDd, String homepage, String jobId, String lnkStmId, String organYn, String plDetAddr, String plbizNm, String repr,
                                            String stmId, String toAcptDd, String updDy, String wantedAuthNo, String wantedTitle) {
        return new SenuriServiceDetailDto(
                acptMthdCd, age, clerk, clerkContt, clltPrnnum, createDy, etcItm, frAcptDd, homepage, jobId, lnkStmId,
                organYn, plDetAddr, plbizNm, repr, stmId, toAcptDd, updDy, wantedAuthNo, wantedTitle
        );
    }
}
