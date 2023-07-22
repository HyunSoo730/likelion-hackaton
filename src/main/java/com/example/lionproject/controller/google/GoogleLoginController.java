package com.example.lionproject.controller.google;

import com.example.lionproject.service.google.GoogleLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/oauth2")
@RequiredArgsConstructor
public class GoogleLoginController {

    private final GoogleLoginService loginService;

    @GetMapping("code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        loginService.socialLogin(code, registrationId);

    }
}
