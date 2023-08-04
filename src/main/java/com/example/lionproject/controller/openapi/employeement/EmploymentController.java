package com.example.lionproject.controller.openapi.employeement;

import com.example.lionproject.domain.dto.EmploymentDto;
import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmploymentController {

    private final WebClientService webClientService;

    @GetMapping("/employeement/test")
    public String returnEntity(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        String res = webClientService.employmentSupport(startIndex, endIndex);
        return res;
    }

    @GetMapping("/employment")
    public ResponseEntity<EmploymentDto> returnDto(@RequestParam Integer startIndex, @RequestParam Integer endIndex) {
        EmploymentDto employmentDto = webClientService.returnEmploymentDto(startIndex, endIndex);
        return new ResponseEntity<>(employmentDto, HttpStatus.OK);
    }
}
