package com.websolution.api.controller;

import com.websolution.api.entity.User;
import com.websolution.api.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // 관리자만 접근 가능
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/pending-users")
    public ResponseEntity<List<User>> getPendingUsers() {
        return ResponseEntity.ok(adminService.getPendingUsers());
    }

    @PutMapping("/approve/{userId}")
    public ResponseEntity<?> approveUser(@PathVariable Long userId) {
        adminService.approveUser(userId);
        return ResponseEntity.ok("회원가입 승인 완료");
    }
}
