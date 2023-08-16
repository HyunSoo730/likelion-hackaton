package com.example.lionproject.controller.kakao;

import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.oauth.OauthToken;
import com.example.lionproject.service.kakao.KakaoLoginService;
import com.example.lionproject.service.kakao.KakaoMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j  // 정보 확인
public class KakaoController {

    private final KakaoLoginService kakaoLoginService;
    private final KakaoMessageService kakaoMessageService;
    private static String access_token = "";

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallBack(@RequestParam("code") String code) {
        //POST방식으로 key=value 데이터를 요청(카카오 쪽으로)
        OauthToken oauthToken = kakaoLoginService.getAccessToken(code);
//        User user = userService.save(oauthToken.getAccess_token());
        String jwtToken = kakaoLoginService.save(oauthToken.getAccess_token());
        //발급받은 access token으로 카카오 회원 정보 DB 저장 후 JWT 생성
        access_token = oauthToken.getAccess_token();

        return jwtToken;
    }

    /**
     * 액세스 토큰으로 로그인 진행.
     */
    @GetMapping("/auth/kakao/callback/access")
    public String kakaoCallBackAccess(@RequestParam("accessToken") String accessToken) {

        String jwtToken = kakaoLoginService.save(accessToken);
        //발급받은 access token으로 카카오 회원 정보 DB 저장 후 JWT 생성
        access_token = accessToken;

        return jwtToken;
    }

    /**
     * 사용자 정보 반환. 카카오 정보 + 닉네임
     * 사용자 정보 요청오면 토큰을 복호화해서 정보 가져온 후에 반환.
     */
    @PostMapping("/auth/kakao/token")
    public KakaoMember userInfo(@RequestHeader("Authorization") String accessToken) {

        //JWT 토큰 검증
        log.info("프론트로부터 들어오는 token, {}", accessToken);
        KakaoMember user = kakaoLoginService.validateToken(accessToken);
        return user;
    }

    /**
     * 카카오톡 텍스트 메시지 보내기
     */
    @PostMapping("/kakao/send_message/text")
    public String returnTextMessage(@RequestBody KakaoMessageTextRequest request) {
//        String accessToken = service.getAccessToken(code).getAccess_token();
        log.info("access_token is {}", access_token);
        String res = kakaoMessageService.sendTextMessage(access_token, request);
        return res;
    }

    /**
     * 카카오톡 피드 메시지 보내기
     */
    @PostMapping("/kakao/send_message/feed")
    public String returnFeedMessage(@RequestBody KakaoMessageFeedRequest request) {
        log.info("access_token is {}", access_token);
        String res = kakaoMessageService.sendFeedMessage(access_token, request);
        return res;
    }

    /**
     * 프론트로부터 액세스 토큰 받아와서 로그아웃 진행.
     */
    @GetMapping("/auth/kakao/logout")
    public String logout(@RequestParam("accessToken") String access_token) {
        String res = kakaoLoginService.logoutKakaoUser(access_token);
        return res;
    }
}
