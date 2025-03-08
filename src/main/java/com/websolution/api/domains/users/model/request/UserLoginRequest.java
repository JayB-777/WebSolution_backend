package com.websolution.api.domains.users.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    private String loginId;
    private String password;
}