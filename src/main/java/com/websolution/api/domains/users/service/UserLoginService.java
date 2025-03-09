package com.websolution.api.domains.users.service;

import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import com.websolution.api.domains.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean authenticate(String loginId, String rawPassword) {
        Optional<User> userOptional = userRepository.findByLoginId(loginId);

        // 사용자가 존재하지 않으면 false 반환
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        // PENDING 상태의 사용자는 로그인 불가
        if (user.getRole() == Role.PENDING) {
            return false;
        }

        // 승인된 사용자만 비밀번호 검증 수행
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}