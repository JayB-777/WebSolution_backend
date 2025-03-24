package com.websolution.api.domains.users.model.response;

import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse {
    private String loginId;
    private String accessToken;
    private String refreshToken;
    private Role role;
    
    public static UserLoginResponse fromEntity(User user, String accessToken, String refreshToken) {
        return new UserLoginResponse(
            user.getLoginId(),
            accessToken,
            refreshToken,
            user.getRole()
        );
    }
}
