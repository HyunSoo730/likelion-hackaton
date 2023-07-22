package com.example.lionproject.domain.google;

import com.example.lionproject.domain.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class GoogleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role;


    @CreationTimestamp
    private Timestamp timestamp;

    public GoogleMember(String email, String name, String picture, Role role) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.role = role;
    }
}
