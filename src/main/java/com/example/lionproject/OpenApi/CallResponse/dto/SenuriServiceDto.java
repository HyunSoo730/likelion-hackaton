package com.example.lionproject.OpenApi.CallResponse.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SenuriServiceDto {

    private String acptMthd;
    private String deadline;
    private String emplymShp;
    private String emplymShpNm;
    private String frDd;
    private String jobId;
    private String oranNm;
    private String organYn;
    private String recrtTitle;
    private String stmId;
    private String stmNm;
    private String toDd;
    private String workPlc;

    public static SenuriServiceDto of(String acptMthd, String deadline, String emplymShp, String emplymShpNm, String frDd, String jobId, String oranNm, String organYn,
                            String recrtTitle, String stmId, String stmNm, String toDd, String workPlc) {
        return new SenuriServiceDto(
                acptMthd, deadline, emplymShp, emplymShpNm, frDd, jobId, oranNm, organYn,
                recrtTitle, stmId, stmNm, toDd, workPlc
        );
    }

}
