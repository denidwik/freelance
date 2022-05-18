package com.project.freelance.service;

import com.project.freelance.controller.auth.login.JwtAuthenticationResponse;
import com.project.freelance.controller.auth.login.LoginRequest;
import com.project.freelance.exception.InvalidRequestException;

public interface AuthLoginService {
    JwtAuthenticationResponse execute(LoginRequest loginRequest) throws InvalidRequestException;
}
