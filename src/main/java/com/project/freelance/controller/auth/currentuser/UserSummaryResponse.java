package com.project.freelance.controller.auth.currentuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class UserSummaryResponse {
    private Long userId;
    private String userName;
    private String email;
}
