package com.example.lionproject.controller.openapi.senuri.interest_area;

import com.example.lionproject.controller.openapi.senuri.SenuriServiceDetailCheckResponse;
import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.kakao.KakaoMemberFav;
import com.example.lionproject.repository.kakao.KakaoMemberFavRepository;
import com.example.lionproject.repository.kakao.KakaoRepository;
import com.example.lionproject.repository.senuri.SenuriServiceDetailRepository;
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
    private final SenuriServiceDetailRepository senuriServiceDetailRepository;

    /**
     * 관심지역 구직정보 가져오는 api
     * 사용자가 저장해놓은 관심 지역에 해당하는 구직 정보 가져오기
     */
    @GetMapping("/all_data")
    public List<SenuriServiceDetailCheckResponse> returnInterestAreaData(@RequestParam("userId") Long userId) {
        KakaoMember findMember = kakaoRepository.findByUserId(userId);
        List<KakaoMemberFav> allByKakaoMember = kakaoMemberFavRepository.findAllByKakaoMember(findMember);
        List<String> areaNames = allByKakaoMember.stream()
                .map(KakaoMemberFav::getAreaName)
                .collect(Collectors.toList());
        log.info("areaNames = {}", areaNames);
        List<SenuriServiceDetailCheckResponse> res = senuriServiceDetailRepository.findFilteredInterestArea(areaNames).stream().map(this::convertToResponse)
                .limit(150)
                .collect(Collectors.toList());
        return res;
    }

    private SenuriServiceDetailCheckResponse convertToResponse(SenuriServiceDetailCheck detailCheck) {

        SenuriServiceDetailCheckResponse response = new SenuriServiceDetailCheckResponse();
        response.setAcptMthdCd(detailCheck.getAcptMthdCd());
        response.setClerk(detailCheck.getClerk());
        response.setClerkContt(detailCheck.getClerkContt());
        response.setClltPrnnum(detailCheck.getClltPrnnum());
        response.setEtcItm(detailCheck.getEtcItm());
        response.setFrAcptDd(detailCheck.getFrAcptDd());
        response.setPlDetAddr(detailCheck.getPlDetAddr());
        response.setPlbizNm(detailCheck.getPlbizNm());
        response.setToAcptDd(detailCheck.getToAcptDd());
        response.setWantedTitle(detailCheck.getWantedTitle());
        response.setEmplymShpNm(detailCheck.getEmplymShpNm());
        response.setDeadline(detailCheck.getDeadline());
        return response;
    }
}
