package com.example.lionproject.controller.kakao;

import com.example.lionproject.service.kakao.KakaoMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.lionproject.domain.kakao.KakaoMessageRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoMessageController {
    private final KakaoMessageService messageService;

    public KakaoMessageController(KakaoMessageService messageService) {
        this.messageService = messageService;
    }
    @PostMapping("/kakao/message")
    public  void sendMessage(@RequestBody KakaoMessageRequest request) {
        messageService.sendMessage(request);
    }
}
