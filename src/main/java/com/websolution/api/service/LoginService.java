package com.websolution.api.service;

import com.websolution.api.entity.Role;
import com.websolution.api.entity.User;
import com.websolution.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean authenticate(String userName, String rawPassword) {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // PENDING 상태의 사용자는 로그인 불가
        if (user.getRole() == Role.PENDING) {
            throw new RuntimeException("관리자 승인 대기 중인 사용자입니다."); // 로그인 실패 처리
        }

        // 승인된 사용자만 비밀번호 검증 수행
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}