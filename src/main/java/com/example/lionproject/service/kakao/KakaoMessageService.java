package com.example.lionproject.service.kakao;
import com.example.lionproject.domain.kakao.KakaoMessageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoMessageService {
    private final RestTemplate restTemplate;

    public KakaoMessageService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage(KakaoMessageRequest request) {
        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        // Request Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(request.getToken());

        // Request Body 설정
        String requestBody = String.format(
                "{\"object_type\": \"text\"," +
                        "\"text\": \"%s\"," +
                        "\"link\": {\"web_url\": \"%s\"}}", request.getMessage(), request.getWebUrl()
        );

        // HttpEntity 생성
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 카카오톡 메시지 보내기 요청 (text)
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("메시지를 성공적으로 보냈습니다.");
        } else {
            System.out.println("에러 발생! 이유: " + response.getBody());
        }
    }

}
