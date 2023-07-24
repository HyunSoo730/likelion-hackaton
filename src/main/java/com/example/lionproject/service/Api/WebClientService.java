package com.example.lionproject.service.Api;

import com.example.lionproject.domain.WebClientDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Transactional
@Slf4j
public class WebClientService {

    private final String BASE_URL = "http://openAPI.seoul.go.kr:8088";

    @Value("${api.hyunsoo.key}")
    private String api_key;


    public WebClientDTO get(Integer startIndex, Integer endIndex) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        //이렇게 URI의 빌드 설정을 완료하면 WebClient의 인스턴스를 생성할 때 해당 UriBuilder로 uri를 만든다고 설정하면 된다.
        // 기본 세팅 진행
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //필요한 값들 요청.
        WebClientDTO response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("{api_key}/json/ListPublicReservationCulture/{startIndex}/{endIndex}")
                        .build(api_key, startIndex, endIndex))
                .retrieve()
                .bodyToMono(WebClientDTO.class)
                .block();

        /**
         * 결과 확인 log
         */
        log.info("반환되는 값 : {}", response.toString());
        return response;
    }

    public String get2(Integer startIndex, Integer endIndex) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        //이렇게 URI의 빌드 설정을 완료하면 WebClient의 인스턴스를 생성할 때 해당 UriBuilder로 uri를 만든다고 설정하면 된다.
        // 기본 세팅 진행
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //필요한 값들 요청.
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("{api_key}/json/ListPublicReservationCulture/{startIndex}/{endIndex}")
                        .build(api_key, startIndex, endIndex))
                .retrieve()
                .bodyToMono(String.class)
                .block();


        /**
         * 결과 확인 log
         */
        log.info("반환되는 값 : {}", response.toString());
        return response;
    }

}
