package com.websolution.api.domains.admin.service;

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

    public void approveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
