package com.example.lionproject.controller.kakao;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoMessageFeedRequest {

    private String object_type;
    private Content content;
    private List<Button> buttons;
    private ItemContent item_content;  // 추가된 부분

    // 생성자, getter 및 setter 메서드는 생략

    // 내부 클래스 정의

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private String title;
        private String description;
        private String image_url;
        private int image_width;
        private int image_height;
        private Link link;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Link {
        private String web_url;
        private String mobile_web_url;
        private String android_execution_params;
        private String ios_execution_params;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Button {
        private String title;
        private Link link;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemContent {
        private List<Item> items;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String item;
        private String item_op;
    }
}
