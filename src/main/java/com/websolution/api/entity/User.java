package com.websolution.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; //Primary Key는 userId (숫자 ID)

    @Column(nullable = false, unique = true)
    private String loginId; // 사용자가 로그인 시 사용하는 ID

    @Column(nullable = false)
    private String userName; //사용자 이름 (예: "홍길동")

    @Column(nullable = false)
    private String password; // BCrypt 암호화된 비밀번호 저장

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;


    @Column(nullable = true)
    private String company;

    @Enumerated(EnumType.STRING)
    private Role role = Role.PENDING; // 기본값: PENDING (관리자 승인 필요)
}