package com.example.lionproject.controller.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoMessageTextRequest {

    private String object_type;
    private String text;
    private Map<String, String> link;
    private String button_title;


}
