package com.example.lionproject.domain.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoMessageRequest {
    private String token;
    private String webUrl;
    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String weburl) {
        this.webUrl = weburl;
    }
}
