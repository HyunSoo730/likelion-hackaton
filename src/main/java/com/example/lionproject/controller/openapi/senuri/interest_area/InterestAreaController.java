package com.example.lionproject.controller.openapi.senuri.interest_area;

import com.example.lionproject.controller.openapi.senuri.SenuriServiceDetailCheckResponse;
import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.kakao.KakaoMemberFav;
import com.example.lionproject.repository.kakao.KakaoMemberFavRepository;
import com.example.lionproject.repository.kakao.KakaoRepository;
import com.example.lionproject.repository.senuri.SenuriServiceDetailRepository;
import com.example.lionproject.service.kakao.KakaoLoginService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Area;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional
@RequestMapping("/api/interest_area")
public class InterestAreaController {

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


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Request {
        Long userId;
        List<String> area;
    }

    /**
     * 관심지역 등록 - 최대 3개지역 (서울시로 한정)
     */
    @PostMapping("/update")
    public String registerArea(@RequestBody Request request) {
        KakaoMember findMember = kakaoRepository.findByUserId(request.getUserId());

        // 사용자 관심지역 전체 삭제
        kakaoMemberFavRepository.deleteByKakaoMember(findMember);
        List<String> area = request.getArea(); //등록할 area
        List<KakaoMemberFav> res = area.stream().map(areaName -> convertToKakaoMemberFav(findMember, areaName))
                .collect(Collectors.toList());
        //사용자 관심지역 추가
        kakaoMemberFavRepository.saveAll(res);

        // 3개인 사람이라면... 다 삭제 후 다 등록
        // 그 외에는 userId 있는지 검사 후에 해당 있으면 추가

        return "등록완료";
    }

    @PostMapping("/delete")
    public String deleteArea(@RequestBody Request request) {
        KakaoMember findMember = kakaoRepository.findByUserId(request.userId);

        // 요청한 지역 전체 삭제
        kakaoMemberFavRepository.deleteByKakaoMember(findMember);

        return "삭제 완료";
    }

    /**
     * 데이터 변환
     */
    private KakaoMemberFav convertToKakaoMemberFav(KakaoMember member, String areaName) {
        KakaoMemberFav fav = KakaoMemberFav.of(member, areaName);
        return fav;
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
