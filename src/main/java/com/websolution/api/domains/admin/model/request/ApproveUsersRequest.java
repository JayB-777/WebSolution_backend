package com.websolution.api.domains.admin.model.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApproveUsersRequest {
    private List<String> userLoginIds;
}
