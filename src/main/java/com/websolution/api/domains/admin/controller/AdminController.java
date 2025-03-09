package com.websolution.api.domains.admin.controller;

import com.websolution.api.common.response.BaseResponse;
import com.websolution.api.domains.admin.model.response.PendingUserResponse;
import com.websolution.api.domains.admin.service.AdminLoginService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')") // 관리자만 접근 가능
public class AdminController {
    private final AdminLoginService adminService;

    @GetMapping("/pending-users")
    public ResponseEntity<BaseResponse<List<PendingUserResponse>>> getPendingUsers() {
        List<PendingUserResponse> pendingUsers = adminService.getPendingUsers();
        return ResponseEntity.ok(new BaseResponse<>(pendingUsers));
    }

    @PutMapping("/approve/{userId}")
    public ResponseEntity<?> approveUser(@PathVariable Long userId) {
        adminService.approveUser(userId);
        return ResponseEntity.ok("회원가입 승인 완료");
    }
}
