package com.project.freelance.controller.auth.singup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class SignUpRequest {
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;
}
