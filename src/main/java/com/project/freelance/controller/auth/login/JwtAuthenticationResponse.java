package com.project.freelance.controller.auth.login;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private long roleId;
    private String roleName;
}
