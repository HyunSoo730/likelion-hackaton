package com.example.lionproject.controller;

import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceRawResponse;
import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final WebClientService service;

    @GetMapping(value = "/test_service", produces = MediaType.APPLICATION_XML_VALUE)
    public SenuriServiceRawResponse returnData(@RequestParam int num, @RequestParam int page) {
        SenuriServiceRawResponse res = service.CallSenuriListService(num, page);
        return res;
    }

    @GetMapping("/test_service2")
    public String returnStringData(@RequestParam int num, @RequestParam int page) {
        String res = service.CallSenuriListServiceString(num, page);
        return res;
    }
}
