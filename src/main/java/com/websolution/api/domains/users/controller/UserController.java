package com.websolution.api.domains.users.controller;

import com.websolution.api.common.response.BaseResponse;
import com.websolution.api.common.response.BaseResponseStatus;
import com.websolution.api.domains.entity.User;
import com.websolution.api.domains.users.model.dto.UserDto;
import com.websolution.api.domains.users.model.request.UserLoginRequest;
import com.websolution.api.domains.users.service.UserLoginService;
import com.websolution.api.domains.users.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
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
    public BaseResponse<String> register(@RequestBody UserDto userDto) {
        User savedUser = userService.register(userDto);
        return new BaseResponse<>(savedUser.getLoginId());
    }

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody UserLoginRequest request) {
        boolean isAuthenticated = loginService.authenticate(request.getLoginId(), request.getPassword());
        if (!isAuthenticated) {
            return new BaseResponse<>(BaseResponseStatus.LOGIN_FAILED);
        }
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, "로그인 성공");
    }
}