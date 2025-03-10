package com.websolution.api.domains.users.service;

import com.websolution.api.common.response.BaseResponseStatus;
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

    public User authenticate(String loginId, String rawPassword) {
        // 1. 사용자 정보 조회
        Optional<User> userOptional = userRepository.findByLoginId(loginId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException(BaseResponseStatus.NOT_FOUND_LOGIN_ID.getMessage());
        }

        User user = userOptional.get();

        // 2. 비밀번호 검증 (틀리면 로그인 실패 처리)
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException(BaseResponseStatus.LOGIN_FAILED.getMessage());
        }

        // 3. PENDING 상태 체크 (비밀번호가 맞은 후에 검증)
        if (user.getRole() == Role.PENDING) {
            throw new IllegalStateException(BaseResponseStatus.LOGIN_PENDING.getMessage());
        }

        // 4. 로그인 성공
        return user;
    }
}