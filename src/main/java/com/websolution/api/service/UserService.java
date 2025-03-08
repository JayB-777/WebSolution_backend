package com.websolution.api.service;

import com.websolution.api.dto.UserDto;
import com.websolution.api.entity.Role;
import com.websolution.api.entity.User;
import com.websolution.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserDto userDto) {
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