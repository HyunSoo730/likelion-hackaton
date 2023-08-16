package com.example.lionproject.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SenuriServiceDetailCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acptMthdCd; //접수방법
    private String age;//연령
    private String clerk;//담당자
    private String clerkContt;//담당자연락처
    private String clltPrnnum;//채용공고 모집인원
    private String createDy;//생성일자
    private String etcItm;//기타사항 or 우대사항
    private String frAcptDd;//시작접수일
    private String homepage;//홈페이지주소
    private String jobId;//채용공고ID
    private String lnkStmId;//연계시스템ID
    private String organYn;//대민 or 업무 구분값
    private String plDetAddr;//주소
    private String plbizNm;//사업장명
    private String repr;//담당자
    private String stmId;//시스템ID
    private String toAcptDd;//종료접수일
    private String updDy;//변경일자
    private String wantedAuthNo;//구인인증번호(채용공고ID값)
    private String wantedTitle;//채용제목
    private String emplymShpNm;  //채용공고 형태명 ex 기간제 일자리.

    public SenuriServiceDetailCheck(String acptMthdCd, String age, String clerk, String clerkContt, String clltPrnnum, String createDy, String etcItm, String frAcptDd, String homepage, String jobId, String lnkStmId, String organYn, String plDetAddr, String plbizNm, String repr, String stmId, String toAcptDd, String updDy, String wantedAuthNo, String wantedTitle) {
        this.acptMthdCd = acptMthdCd.equals("CM0801") ? "온라인" : acptMthdCd.equals("CM0802") ? "이메일" : acptMthdCd.equals("CM0803") ? "팩스" : acptMthdCd.equals("CM0804") ? "방문" : acptMthdCd;
        this.age = age;
        this.clerk = clerk;
        this.clerkContt = clerkContt;
        this.clltPrnnum = clltPrnnum;
        this.createDy = createDy;
        this.etcItm = etcItm;
        this.frAcptDd = frAcptDd;
        this.homepage = homepage;
        this.jobId = jobId;
        this.lnkStmId = lnkStmId;
        this.organYn = organYn;
        this.plDetAddr = plDetAddr;
        this.plbizNm = plbizNm;
        this.repr = repr;
        this.stmId = stmId;
        this.toAcptDd = toAcptDd;
        this.updDy = updDy;
        this.wantedAuthNo = wantedAuthNo;
        this.wantedTitle = wantedTitle;
    }
}
