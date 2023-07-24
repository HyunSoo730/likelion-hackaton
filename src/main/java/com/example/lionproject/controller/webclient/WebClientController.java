package com.example.lionproject.controller.webclient;

import com.example.lionproject.domain.dto.WebClientDTO;
import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebClientController {


    private final WebClientService service;

    /**
     * 서울시 문화행사 공공서비스예약 정보 Open API JSON 연동 성공.
     */
    @GetMapping("/test")
    public WebClientDTO returnString(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        WebClientDTO res = service.get(startIndex, endIndex);
        return res;
    }

    @GetMapping("/test2")
    public String returnString2(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        String res = service.get2(startIndex, endIndex);
        return res;
    }

    /**
     * 서울시 공공서비스 예약 (종합) 정보
     */
    @GetMapping("/test3")
    public WebClientDTO returnPublicServiceReservationAPI(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        WebClientDTO res = service.getPublicServiceReservation(startIndex, endIndex);
        return res;
    }
}
