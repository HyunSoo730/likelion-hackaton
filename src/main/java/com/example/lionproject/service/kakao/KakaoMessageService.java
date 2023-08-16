package com.example.lionproject.service.kakao;

import com.example.lionproject.controller.kakao.KakaoMessageFeedRequest;
import com.example.lionproject.controller.kakao.KakaoMessageTextRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoMessageService {

    @Value("${kakao.api}")
    private String kakaoApiKey;

    private final WebClient webClient;
    private final String BASE_URL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";


    public String sendTextMessage(String accessToken, KakaoMessageTextRequest request) {

        /**
         * 메시지 보낼 때 필요한 정보들
         *
         */
//        Map<String, Object> linkData = new HashMap<>();
//        linkData.put("web_url", request.getLink().get("web_url"));
//        linkData.put("mobile_web_url", request.getLink().get("mobile_web_url"));
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("object_type", "text");  //text로 고정
//        requestBody.put("text", request.getText());
//        requestBody.put("link", linkData);
//        requestBody.put("button_title", request.getButton_title());

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        try {
            formData.add("template_object", new ObjectMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String res = webClient.mutate()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return res;
    }

    public String sendFeedMessage(String accessToken, KakaoMessageFeedRequest request) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        try {
            formData.add("template_object", new ObjectMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String res = webClient.mutate()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return res;
    }

}
