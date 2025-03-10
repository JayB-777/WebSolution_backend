package com.websolution.api.domains.admin.service;

import com.websolution.api.common.response.BaseResponseStatus;
import com.websolution.api.domains.admin.model.response.ApprovedUserResponse;
import com.websolution.api.domains.admin.model.response.PendingUserResponse;
import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import com.websolution.api.domains.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final UserRepository userRepository;

    public List<PendingUserResponse> getPendingUsers() {
        return userRepository.findByRole(Role.PENDING);
    }

    public List<ApprovedUserResponse> approveUsers(List<String> userLoginIds) {
        // PENDING 상태의 유저만 필터링하여 가져오기
        List<User> users =
                userRepository.findByLoginIdIn(userLoginIds).stream().filter(user -> user.getRole() == Role.PENDING)
                        .toList();

        if (users.isEmpty()) {
            throw new IllegalArgumentException(BaseResponseStatus.NOT_FOUND_PENDING_USER.getMessage());
        }

        // 역할 변경
        users.forEach(user -> user.setRole(Role.USER));
        userRepository.saveAll(users);

        // 승인된 유저들의 정보를 ApprovedUserResponse 리스트로 변환 후 반환
        return users.stream().map(ApprovedUserResponse::fromEntity).toList();
    }
}
