package com.websolution.api.domains.users.controller;

import com.websolution.api.domains.entity.User;
import com.websolution.api.domains.users.model.dto.UserDto;
import com.websolution.api.domains.users.model.request.UserLoginRequest;
import com.websolution.api.domains.users.service.UserLoginService;
import com.websolution.api.domains.users.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserRegisterService userService;
    private final UserLoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User savedUser = userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        boolean isAuthenticated = loginService.authenticate(request.getLoginId(), request.getPassword());
        return isAuthenticated ? ResponseEntity.ok("로그인 성공") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }
}