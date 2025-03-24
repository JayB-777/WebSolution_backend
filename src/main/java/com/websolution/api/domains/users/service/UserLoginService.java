package com.websolution.api.domains.users.service;

import com.websolution.api.common.response.BaseResponseStatus;
import com.websolution.api.common.security.JwtTokenProvider;
import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import com.websolution.api.domains.repository.UserRepository;
import com.websolution.api.domains.users.model.response.UserLoginResponse;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserLoginResponse authenticate(String loginId, String rawPassword) {
        // 1. 사용자 정보 조회
        Optional<User> userOptional = userRepository.findByLoginId(loginId);

        // 2. loginId 검증 || 비밀번호 검증 (loginId가 없거나 비밀번호가 틀리면 로그인 실패 처리)
        if (userOptional.isEmpty() || !passwordEncoder.matches(rawPassword, userOptional.get().getPassword())) {
            throw new IllegalArgumentException(BaseResponseStatus.LOGIN_FAILED.getMessage());
        }

        // 3. PENDING 상태 체크 (비밀번호가 맞은 후에 검증)
        if (userOptional.get().getRole() == Role.PENDING) {
            throw new IllegalStateException(BaseResponseStatus.LOGIN_PENDING.getMessage());
        }

        // 4. JWT 토큰 생성
        var authorities =
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userOptional.get().getRole().name()));
        String accessToken = jwtTokenProvider.generateAccessToken(userOptional.get().getLoginId(), authorities);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userOptional.get().getLoginId(), authorities);

        // 5. 로그인 응답 생성 및 반환
        return UserLoginResponse.fromEntity(userOptional.get(), accessToken, refreshToken);
    }
}
