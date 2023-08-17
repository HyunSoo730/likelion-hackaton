package com.example.lionproject.controller.openapi.senuri.interest_area;

import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.kakao.KakaoMemberFav;
import com.example.lionproject.repository.kakao.KakaoMemberFavRepository;
import com.example.lionproject.repository.kakao.KakaoRepository;
import com.example.lionproject.service.kakao.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/interest_area")
public class InterestAreaController {

    private final KakaoLoginService kakaoLoginService;
    private final KakaoMemberFavRepository kakaoMemberFavRepository;
    private final KakaoRepository kakaoRepository;

    /**
     * 관심지역 구직정보 가져오는 api
     * 사용자가 저장해놓은 관심 지역에 해당하는 구직 정보 가져오기
     */
    @GetMapping("/all_data")
    public String returnInterestAreaData(@RequestParam("userId") Long userId) {
        KakaoMember findMember = kakaoRepository.findByUserId(userId);
        List<KakaoMemberFav> allByKakaoMember = kakaoMemberFavRepository.findAllByKakaoMember(findMember);
        List<String> areaNames = allByKakaoMember.stream()
                .map(KakaoMemberFav::getAreaName)
                .collect(Collectors.toList());
        return null;
    }
}
