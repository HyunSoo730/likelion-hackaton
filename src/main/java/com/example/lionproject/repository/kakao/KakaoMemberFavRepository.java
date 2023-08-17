package com.example.lionproject.repository.kakao;

import com.example.lionproject.domain.kakao.KakaoMemberFav;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoMemberFavRepository extends JpaRepository<KakaoMemberFav, Long> {
}
