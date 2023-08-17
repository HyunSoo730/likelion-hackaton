package com.example.lionproject.controller.alram;

import com.example.lionproject.service.kakao.KakaoMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/alaram")
public class AlarmController {

    private final KakaoMessageService kakaoMessageService;


}
