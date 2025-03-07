package com.websolution.api.controller;

import com.websolution.api.dto.LoginRequest;
import com.websolution.api.dto.UserDto;
import com.websolution.api.entity.User;
import com.websolution.api.service.LoginService;
import com.websolution.api.service.UserService;
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
public class AuthController {
    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User savedUser = userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean isAuthenticated = loginService.authenticate(request.getUsername(), request.getPassword());
        return isAuthenticated ? ResponseEntity.ok("로그인 성공") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }
}