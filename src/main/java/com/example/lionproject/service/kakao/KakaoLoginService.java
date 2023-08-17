package com.example.lionproject.service.kakao;

import com.example.lionproject.domain.dto.EmploymentJsonDto;
import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.kakao.KakaoProfile;
import com.example.lionproject.domain.oauth.OauthToken;
import com.example.lionproject.repository.kakao.KakaoRepository;
import com.example.lionproject.utils.JwtTokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoRepository repository;

    @Value("${kakao.api}")
    private String kakaoApiKey;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @Value("${user.secretkey}")
    private String secretKey;

    @Value("${user.expiredate}")
    private String expireDate;

    private final String KAKAO_LOGOUT_URL = "https://kapi.kakao.com/v1/user/logout";

    private final WebClient webClient;

    /**
     * 로그아웃
     */
    public String logoutKakaoUser(String accessToken) {

        String res = webClient.mutate()
                .baseUrl(KAKAO_LOGOUT_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return res;

    }

    /**
     * 인가코드로 액세스 토큰 발급
     */
    public OauthToken getAccessToken(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoApiKey);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        //4가지 모두 넣어줌.

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers); //body 데이터와 헤더값 가짐.

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //HTTP 응답 (JSON) -> 액세스 토큰 파싱.
        //ObjectMapper를 통해 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        /**
         * 로그 찍어보기
         */
        log.info("kakao access token = {}", oauthToken.getAccess_token());
        log.info("kakao refresh token = {}", oauthToken.getRefresh_token());
        log.info("카카오 다른거 {}", oauthToken.getToken_type());
        return oauthToken;
    }

    /**
     * 카카오 프로필 찾기
     */
    public KakaoProfile findProfile(String token) {

        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + token);
        headers2.add("Authorization", "Bearer " + token);
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2); //body 데이터와 헤더값 가짐.

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );
        //access token 받고 토큰으로 회원 정보까지 조회해서 결과가 response2에 담김.
        log.info("사용자 정보 조회, {}", response2.getBody());

        //ObjectMapper를 통해 객체로 변환
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        /**
         * 로그 찍어보기
         */
        //user 오브젝트 : username, password, email
        log.info("카카오 아이디 {}", kakaoProfile.getId());
        log.info("카카오 이메일 {}", kakaoProfile.getKakao_account().getEmail());
        //해당 정보를 통해 우리 서버에 이제 생성해야함
        log.info("서버 username {}", kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        log.info("서버 이메일 {}", kakaoProfile.getKakao_account().getEmail());
        UUID garbagePassword = UUID.randomUUID();
        log.info("서버에 랜덤으로 집어넣을 패스워드 {}", garbagePassword);

        return kakaoProfile;
    }

    /**
     * jwt 토큰 검증
     */
    public KakaoMember validateToken(String token) {
        Long userId = JwtTokenUtils.fetchUserId(token, secretKey);  // 해당 유저 아이디로 조회
        KakaoMember findUser = repository.findByUserId(userId);

        /**
         * 로그 찍어보기
         */
        log.info("찾은 userId = {}", userId);
        log.info("User 정보 : {}", findUser.getKakaoNickname());

        return findUser;

    }

    /**
     * 카카오 유저 DB에 저장 -> accessToken으로.
     */
    public String save(String accessToken) {
        KakaoProfile profile = findProfile(accessToken);

//        KakaoMember user = repository.findByKakaoEmail(profile.getKakao_account().getEmail());
        KakaoMember user = repository.findFirstByKakaoId(profile.getId());
        //        log.info("profile id = {}", profile.id);

        if (user == null) {  //DB에 없는 경우
            user = new KakaoMember(profile.getId(), profile.getKakao_account().getEmail(), profile.getKakao_account().getProfile().getProfile_image_url(), profile.getKakao_account().getProfile().getNickname());
            user.setAccessToken(accessToken);

            repository.save(user);
        }

        // DB에 저장하거나 이미 존재하던거 가져온 후에

        String jwtToken = JwtTokenUtils.generateToken(user, secretKey, Long.parseLong(expireDate));
        log.info("JWT 토큰 발급 {}", jwtToken);
        return jwtToken;
    }

}
