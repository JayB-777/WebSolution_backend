package com.websolution.api.domains.users.model.response;

import com.websolution.api.domains.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse {
    private String loginId;

    public static UserLoginResponse fromEntity(User user) {
        return new UserLoginResponse(user.getLoginId());
    }
}
