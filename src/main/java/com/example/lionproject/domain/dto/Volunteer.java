package com.example.lionproject.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.List;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
public class Volunteer {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "header")
    private static class Header {
        @XmlElement(name = "resultCode")
        private String resultCode;
        @XmlElement(name = "resultMsg")
        private String resultMsg;
    }

    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "body")
    private static class Body {
        //    @XmlElementWrapper(name = "items")    //items로 감싸겠다는 뜻 !!
        @XmlElement(name = "items")
        private Items items;
    }

    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "items")
    private static class Items {
        @XmlElement(name = "item")
        private List<Item> itemList;
    }


    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "item")
    private static class Item {
        @XmlElement(name = "actBeginTm")
        private String actBeginTm;
        @XmlElement(name = "actEndTm")
        private String actEndTm;
        @XmlElement(name = "actPlace")
        private String actPlace;
        @XmlElement(name = "adultPosblAt")
        private String adultPosblAt;
        @XmlElement(name = "gugunCd")
        private String gugunCd;
        @XmlElement(name = "nanmmbyNm")
        private String nanmmbyNm;
        @XmlElement(name = "noticeBgnde")
        private String noticeBgnde;
        @XmlElement(name = "noticeEndde")
        private String noticeEndde;
        @XmlElement(name = "progrmBgnde")
        private String progrmBgnde;
        @XmlElement(name = "progrmEndde")
        private String progrmEndde;
        @XmlElement(name = "progrmRegistNo")
        private String progrmRegistNo;
        @XmlElement(name = "progrmSj")
        private String progrmSj;
        @XmlElement(name = "progrmSttusSe")
        private String progrmSttusSe;
        @XmlElement(name = "sidoCd")
        private String sidoCd;
        @XmlElement(name = "srvcClCode")
        private String srvcClCode;
        @XmlElement(name = "url")
        private String url;
        @XmlElement(name = "yngbgsPosblAt")
        private String yngbgsPosblAt;
    }

}
