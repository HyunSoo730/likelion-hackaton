package com.example.lionproject.domain.kakao;

import com.example.lionproject.domain.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class KakaoMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private Long kakaoId;
    private String kakaoEmail;
    private String kakaoProfileImg;
    private String kakaoNickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private Timestamp createTime;

    public KakaoMember(Long kakaoId, String kakaoEmail, String kakaoProfileImg, String kakaoNickname) {
        this.kakaoId = kakaoId;
        this.kakaoEmail = kakaoEmail;
        this.kakaoProfileImg = kakaoProfileImg;
        this.kakaoNickname = kakaoNickname;
    }
}
