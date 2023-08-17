package com.example.lionproject.repository.kakao;

import com.example.lionproject.domain.kakao.KakaoMember;
import com.example.lionproject.domain.kakao.KakaoMemberFav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KakaoMemberFavRepository extends JpaRepository<KakaoMemberFav, Long> {

    List<KakaoMemberFav> findAllByKakaoMember(KakaoMember kakaoMember);

}
