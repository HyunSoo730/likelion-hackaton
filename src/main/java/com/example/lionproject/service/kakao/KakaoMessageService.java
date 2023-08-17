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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 피드 메시지 보내기.
     */
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

    /**
     * 만약 커스텀을 해야하는 경우에.
     */
    public String sendFeedCustomMessage(String accessToken, String con) {
        KakaoMessageFeedRequest request = new KakaoMessageFeedRequest();
        request.setObject_type("feed"); //feed 메시지라 여기는 고정으로 넣어야함.

        KakaoMessageFeedRequest.Content content = new KakaoMessageFeedRequest.Content();
        content.setTitle(con);
        // 여기서 나머지 정보들을 내가 직접 구인 구직 정보 레포지토리에서 꺼내와서 설정해주면 됨.

        KakaoMessageFeedRequest.Button webButton = new KakaoMessageFeedRequest.Button();
        webButton.setTitle("웹으로 이동");
        KakaoMessageFeedRequest.Link webLink = new KakaoMessageFeedRequest.Link();
        webLink.setWeb_url("http://www.daum.net");
        webLink.setMobile_web_url("http://m.daum.net");
        webButton.setLink(webLink);

        KakaoMessageFeedRequest.Button appButton = new KakaoMessageFeedRequest.Button();
        appButton.setTitle("앱으로 이동");
        KakaoMessageFeedRequest.Link appLink = new KakaoMessageFeedRequest.Link();
        appLink.setAndroid_execution_params("contentId=100");
        appLink.setIos_execution_params("contentId=100");
        appButton.setLink(appLink);

        List<KakaoMessageFeedRequest.Button> buttons = new ArrayList<>();
        buttons.add(webButton);
        buttons.add(appButton);

        request.setContent(content);
        request.setButtons(buttons);

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
