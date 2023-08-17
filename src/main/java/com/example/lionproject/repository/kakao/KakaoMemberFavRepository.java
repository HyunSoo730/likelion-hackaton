package com.example.lionproject.repository.kakao;

import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.kakao.KakaoMemberFav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KakaoMemberFavRepository extends JpaRepository<KakaoMemberFav, Long> {

    List<KakaoMemberFav> findAllByKakaoMember(KakaoMember kakaoMember);

    void deleteByKakaoMember(KakaoMember kakaoMember);

//    @Modifying
//    @Query("DELETE FROM KakaoMemberFav k WHERE k.kakaoMember = :kakaoMember and k.areaName in :area")
//    void deleteKakaoMember(@Param("kakaoMember") KakaoMember member, @Param("area") List<String> area);

    @Query("SELECT k FROM KakaoMemberFav k  JOIN FETCH k.kakaoMember WHERE k.kakaoMember.userId = :userId")
    List<KakaoMemberFav> findAllByKakaoMemberUserId(Long userId);

}
