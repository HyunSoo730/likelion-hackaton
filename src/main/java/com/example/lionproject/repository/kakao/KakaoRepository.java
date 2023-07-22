package com.example.lionproject.repository.kakao;

import com.example.lionproject.domain.kakao.KakaoMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoRepository extends JpaRepository<KakaoMember, Long> {
    KakaoMember findByKakaoEmail(String email);

    KakaoMember findByUserId(Long id);
}
