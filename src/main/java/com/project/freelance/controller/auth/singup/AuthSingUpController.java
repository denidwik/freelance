package com.project.freelance.controller.auth.singup;

import com.project.freelance.constant.ApiConstant;
import com.project.freelance.service.AuthSignUpService;
import com.project.freelance.service.impl.AuthSignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthSingUpController {

    private final AuthSignUpService authSignUpService;

    @Autowired
    public AuthSingUpController(AuthSignUpServiceImpl authSignUpService) {
        this.authSignUpService = authSignUpService;
    }

    @PostMapping(ApiConstant.SIGNUP)
    public Long register(@RequestBody SignUpRequest signUpRequest) throws Exception {
        return authSignUpService.execute(signUpRequest);
    }
}
