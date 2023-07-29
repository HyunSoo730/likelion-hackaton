package com.example.lionproject.service.kakao;

import com.example.lionproject.domain.kakao.KakaoMessageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KakaoMessageServiceTest {
    @Test
    public void testSendMessage() {
        //given
        String token = "testToken";
        String message = "testMessage";
        String webUrl = "testWebUrl";

        KakaoMessageRequest request = new KakaoMessageRequest();
        request.setToken(token);
        request.setMessage(message);
        request.setWebUrl(webUrl);

        //mock restTemplate
        RestTemplate restTemplate = mock(RestTemplate.class);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);

        KakaoMessageService messageService = new KakaoMessageService(restTemplate);

        //when
        messageService.sendMessage(request);

        //then
        String expectedUrl="https://kapi.kakao.com/v2/api/talk/memo/default/send";
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.setContentType(MediaType.APPLICATION_JSON);
        expectedHeaders.setBearerAuth(token);

        String expectedRequestBody = String.format(
                "{\"object_type\": \"text\"," +
                        "\"text\": \"%s\"," +
                        "\"link\": {\"web_url\": \"%s\"}}", message, webUrl
        );
        HttpEntity<String> expectedEntity = new HttpEntity<>(expectedRequestBody, expectedHeaders);
        verify(restTemplate).postForEntity(expectedUrl, expectedEntity, String.class);
    }
}
