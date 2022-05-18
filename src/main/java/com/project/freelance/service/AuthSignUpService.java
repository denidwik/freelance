package com.project.freelance.service;

import com.project.freelance.controller.auth.singup.SignUpRequest;
import com.project.freelance.exception.InvalidRequestException;

public interface AuthSignUpService {
    Long execute(SignUpRequest signUpRequest) throws Exception;
}
