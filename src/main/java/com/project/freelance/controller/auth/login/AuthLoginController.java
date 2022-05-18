package com.project.freelance.controller.auth.login;

import com.project.freelance.constant.ApiConstant;
import com.project.freelance.exception.InvalidRequestException;
import com.project.freelance.service.AuthLoginService;
import com.project.freelance.service.impl.AuthLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AuthLoginController {

    private final AuthLoginService authLoginService;

    @Autowired
    public AuthLoginController(AuthLoginServiceImpl authLoginService) {
        this.authLoginService = authLoginService;
    }

    @PostMapping(ApiConstant.LOGIN)
    public JwtAuthenticationResponse login(@RequestBody @Valid LoginRequest loginRequest) throws InvalidRequestException {
        return authLoginService.execute(loginRequest);
    }
}
