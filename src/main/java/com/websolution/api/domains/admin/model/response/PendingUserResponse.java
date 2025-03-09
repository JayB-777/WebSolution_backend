package com.websolution.api.domains.admin.model.response;

import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PendingUserResponse {
    private String loginId;
    private String userName;
    private String email;
    private String phoneNumber;
    private String company;
    private Role role; // 기본적으로 PENDING 상태

    public static PendingUserResponse fromEntity(User user) {
        return new PendingUserResponse(user.getLoginId(), user.getUserName(), user.getEmail(), user.getPhoneNumber(),
                user.getCompany(), user.getRole());
    }
}