package com.example.lionproject.domain.dto;

import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceDetailRawResponse;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
public class SenuriServiceDetailResponse {

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

        @XmlElement(name = "numOfRows")
        private Integer numOfRows;

        @XmlElement(name = "pageNo")
        private Integer pageNo;

        @XmlElement(name = "totalCount")
        private Integer totalCount;

        @Setter
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Items{

            @XmlElement(name = "item")
            private Item item;

            @Setter
            @ToString
            @NoArgsConstructor
            @AllArgsConstructor
            private static class Item{

                @XmlElement(name = "acptMthdCd", defaultValue = "empty")
                private String acptMthdCd;

                @XmlElement(name = "age", defaultValue = "empty")
                private String age;

                @XmlElement(name = "clerk", defaultValue = "empty")
                private String clerk;

                @XmlElement(name = "clerkContt", defaultValue = "empty")
                private String clerkContt;

                @XmlElement(name = "clltPrnnum", defaultValue = "empty")
                private String clltPrnnum;

                @XmlElement(name = "createDy", defaultValue = "empty")
                private String createDy;

                @XmlElement(name = "etcItm", defaultValue = "empty")
                private String etcItm;

                @XmlElement(name = "frAcptDd", defaultValue = "empty")
                private String frAcptDd;

                @XmlElement(name = "homepage", defaultValue = "empty")
                private String homepage;

                @XmlElement(name = "jobId", defaultValue = "empty")
                private String jobId;

                @XmlElement(name = "lnkStmId", defaultValue = "empty")
                private String lnkStmId;

                @XmlElement(name = "organYn", defaultValue = "empty")
                private String organYn;

                @XmlElement(name = "plDetAddr", defaultValue = "empty")
                private String plDetAddr;

                @XmlElement(name = "plbizNm", defaultValue = "empty")
                private String plbizNm;

                @XmlElement(name = "repr", defaultValue = "empty")
                private String repr;

                @XmlElement(name = "stmId", defaultValue = "empty")
                private String stmId;

                @XmlElement(name = "toAcptDd", defaultValue = "empty")
                private String toAcptDd;

                @XmlElement(name = "updDy", defaultValue = "empty")
                private String updDy;

                @XmlElement(name = "wantedAuthNo", defaultValue = "empty")
                private String wantedAuthNo;

                @XmlElement(name = "wantedTitle", defaultValue = "empty")
                private String wantedTitle;
            }

        }
    }

}
