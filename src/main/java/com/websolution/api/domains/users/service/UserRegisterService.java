package com.websolution.api.domains.users.service;

import com.websolution.api.common.response.BaseResponseStatus;
import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import com.websolution.api.domains.repository.UserRepository;
import com.websolution.api.domains.users.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserDto userDto) {

        // loginId 중복 검사
        if (userRepository.existsByLoginId(userDto.getLoginId())) {
            throw new IllegalArgumentException(BaseResponseStatus.DUPLICATE_LOGIN_ID.getMessage());
        }

        // email 중복 검사
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException(BaseResponseStatus.DUPLICATE_EMAIL.getMessage());
        }

        User user = new User();
        user.setLoginId(userDto.getLoginId());
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // 비밀번호 암호화
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCompany(userDto.getCompany());
        user.setRole(Role.PENDING); // 기본값: PENDING

        return userRepository.save(user);
    }


}