package com.example.lionproject.controller.kakao;

import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.oauth.OauthToken;
import com.example.lionproject.service.kakao.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j  // 정보 확인
public class KakaoLoginController {

    private final KakaoLoginService service;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallBack(@RequestParam("code") String code) {
        //POST방식으로 key=value 데이터를 요청(카카오 쪽으로)
        OauthToken oauthToken = service.getAccessToken(code);
//        User user = userService.save(oauthToken.getAccess_token());
        String jwtToken = service.save(oauthToken.getAccess_token());
        //발급받은 access token으로 카카오 회원 정보 DB 저장 후 JWT 생성

        return jwtToken;
    }

    /**
     * 사용자 정보 반환. 카카오 정보 + 닉네임
     * 사용자 정보 요청오면 토큰을 복호화해서 정보 가져온 후에 반환.
     */
    @PostMapping("/auth/kakao/token")
    public KakaoMember userInfo(@RequestHeader("Authorization") String token) {
        //JWT 토큰 검증
        log.info("프론트로부터 들어오는 token, {}", token);
        KakaoMember user = service.validateToken(token);
        return user;
    }
}
