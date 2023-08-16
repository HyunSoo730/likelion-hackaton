package com.example.lionproject.OpenApi.CallResponse.Raw;

import com.example.lionproject.OpenApi.CallResponse.dto.SenuriServiceDto;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
public class SenuriServiceRawResponse {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Header{

        @XmlElement(name = "resultCode")
        private String resultCode;

        @XmlElement(name = "resultMsg")
        private String resultMsg;

    }

    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Body{

        @XmlElement(name = "items")
        private Items items;

        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Items{

            @XmlElement(name = "item")
            private List<Item> item;

            @Setter
            @ToString
            @NoArgsConstructor
            @AllArgsConstructor
            private static class Item{

                @XmlElement(name = "acptMthd")
                private String acptMthd;

                @XmlElement(name = "deadline")
                private String deadline;

                @XmlElement(name = "emplymShp")
                private String emplymShp;

                @XmlElement(name = "emplymShpNm")
                private String emplymShpNm;

                @XmlElement(name = "frDd")
                private String frDd;

                @XmlElement(name = "jobId")
                private String jobId;

                @XmlElement(name = "oranNm")
                private String oranNm;

                @XmlElement(name = "organYn")
                private String organYn;

                @XmlElement(name = "recrtTitle")
                private String recrtTitle;

                @XmlElement(name = "stmId")
                private String stmId;

                @XmlElement(name = "stmNm")
                private String stmNm;

                @XmlElement(name = "toDd")
                private String toDd;

                @XmlElement(name = "workPlc")
                private String workPlc;
            }

        }
    }

    public String fetchResultCode(){
        return this.header.resultCode;
    }

    public List<SenuriServiceDto> toListDto(){
        return this.body.items.item.stream()
                .map(i -> SenuriServiceDto.of(
                        i.acptMthd, i.deadline, i.emplymShp, i.emplymShpNm, i.frDd, i.jobId, i.oranNm, i.organYn,
                        i.recrtTitle, i.stmId, i.stmNm, i.toDd, i.workPlc
                ))
                .collect(Collectors.toList());
    }

    public LocalDate returnToDd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(this.body.items.item.get(0).toDd, formatter);
    }

}
