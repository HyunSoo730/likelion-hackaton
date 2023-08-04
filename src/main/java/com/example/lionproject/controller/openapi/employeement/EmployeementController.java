package com.example.lionproject.controller.openapi.employeement;

import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeementController {

    private final WebClientService webClientService;

    @GetMapping("/employeement/test")
    public String returnEntity(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        String res = webClientService.employeementSupport(startIndex, endIndex);
        return res;
    }
}
