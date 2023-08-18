package com.example.lionproject.domain.kakao;

import com.example.lionproject.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KakaoMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private Long kakaoId;
    private String kakaoEmail;
    private String kakaoProfileImg;
    private String kakaoNickname;
    private String accessToken;

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
