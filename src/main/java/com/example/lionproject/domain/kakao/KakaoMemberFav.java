package com.example.lionproject.domain.kakao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoMemberFav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private KakaoMember kakaoMember;

    private String areaName;

    public static KakaoMemberFav of(KakaoMember kakaoMember, String areaName) {
        return new KakaoMemberFav(null, kakaoMember, areaName);
    }
}
