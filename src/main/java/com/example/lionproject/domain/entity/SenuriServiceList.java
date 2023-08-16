package com.example.lionproject.domain.entity;

import com.example.lionproject.OpenApi.CallResponse.dto.SenuriServiceDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class SenuriServiceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public SenuriServiceList(String acptMthd, String deadline, String emplymShp, String emplymShpNm, String frDd, String jobId, String oranNm, String organYn, String recrtTitle, String stmId, String stmNm, String toDd, String workPlc) {
        this.acptMthd = acptMthd;
        this.deadline = deadline;
        this.emplymShp = emplymShp;
        this.emplymShpNm = emplymShpNm;
        this.frDd = frDd;
        this.jobId = jobId;
        this.oranNm = oranNm;
        this.organYn = organYn;
        this.recrtTitle = recrtTitle;
        this.stmId = stmId;
        this.stmNm = stmNm;
        this.toDd = toDd;
        this.workPlc = workPlc;
    }

    public static SenuriServiceList fromDto(SenuriServiceDto dto) {
        SenuriServiceList value = new SenuriServiceList(
                dto.getAcptMthd(), dto.getDeadline(), dto.getEmplymShp(), dto.getEmplymShpNm(), dto.getFrDd(),
                dto.getJobId(), dto.getOranNm(), dto.getOrganYn(), dto.getRecrtTitle(), dto.getStmId(),
                dto.getStmNm(), dto.getToDd(), dto.getWorkPlc()
        );
        value.emplymShp = value.emplymShp.equals("CM0101") ? "정규직" :
                value.emplymShp.equals("CM0102") ? "계약직" :
                        value.emplymShp.equals("CM0103") ? "시간제 일자리" :
                                value.emplymShp.equals("CM0104") ? "일당직" :
                                        value.emplymShp.equals("CM0105") ? "기타" : value.emplymShp;

        value.emplymShpNm = value.emplymShpNm.equals("CM0101") ? "정규직" :
                value.emplymShpNm.equals("CM0102") ? "계약직" :
                        value.emplymShpNm.equals("CM0103") ? "시간제 일자리" :
                                value.emplymShpNm.equals("CM0104") ? "일당직" :
                                        value.emplymShpNm.equals("CM0105") ? "기타" : value.emplymShpNm;

        value.organYn = value.organYn.equals("N") ? "대민" :
                value.organYn.equals("Y") ? "업무" : value.organYn;

        return value;
    }
}
