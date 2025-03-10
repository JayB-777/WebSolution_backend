package com.websolution.api.domains.admin.model.response;

import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApprovedUserResponse {
    private String loginId;
    private Role role;

    public static ApprovedUserResponse fromEntity(User user) {
        return new ApprovedUserResponse(user.getLoginId(), user.getRole());
    }
}
