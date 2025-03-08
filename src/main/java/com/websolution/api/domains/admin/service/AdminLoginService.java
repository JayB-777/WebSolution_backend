package com.websolution.api.domains.admin.service;

import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import com.websolution.api.domains.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final UserRepository userRepository;

    public List<User> getPendingUsers() {
        return userRepository.findAll().stream().filter(user -> user.getRole() == Role.PENDING)
                .collect(Collectors.toList());
    }

    public void approveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
