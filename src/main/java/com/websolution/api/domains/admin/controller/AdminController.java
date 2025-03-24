package com.websolution.api.domains.admin.controller;

import com.websolution.api.common.response.BaseResponse;
import com.websolution.api.domains.admin.model.request.ApproveUsersRequest;
import com.websolution.api.domains.admin.model.response.ApprovedUserResponse;
import com.websolution.api.domains.admin.model.response.PendingUserResponse;
import com.websolution.api.domains.admin.service.AdminLoginService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // 관리자만 접근 가능
public class AdminController {
    private final AdminLoginService adminService;

    @GetMapping("/pending-users")
    public BaseResponse<List<PendingUserResponse>> getPendingUsers() {
        List<PendingUserResponse> pendingUsers = adminService.getPendingUsers();
        return new BaseResponse<>(pendingUsers);
    }

    @PutMapping("/approve-users")
    public BaseResponse<List<ApprovedUserResponse>> approveUsers(@RequestBody ApproveUsersRequest request) {
        List<ApprovedUserResponse> approvedUserResponses = adminService.approveUsers(request.getUserLoginIds());
        return new BaseResponse<>(approvedUserResponses);
    }
}
