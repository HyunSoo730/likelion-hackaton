package com.example.lionproject.repository.kakao;

import com.example.lionproject.domain.kakao.KakaoMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KakaoRepository extends JpaRepository<KakaoMember, Long> {
    KakaoMember findByKakaoEmail(String email);

    KakaoMember findByUserId(Long id);

    @Query("SELECT DISTINCT k.userId FROM KakaoMember k")
    List<Long> findAllUserId();
}
