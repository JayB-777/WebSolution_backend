package com.websolution.api.domains.users.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long userId;
    private String loginId;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String company;
}