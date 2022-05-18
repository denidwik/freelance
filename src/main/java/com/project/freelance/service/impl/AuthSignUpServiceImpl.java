package com.project.freelance.service.impl;

import com.project.freelance.controller.auth.singup.SignUpRequest;
import com.project.freelance.entity.User;
import com.project.freelance.exception.InvalidRequestException;
import com.project.freelance.gateway.UserJPAGateway;
import com.project.freelance.service.AuthSignUpService;
import com.project.freelance.util.Constant;
import com.project.freelance.util.LongUtils;
import com.project.freelance.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthSignUpServiceImpl implements AuthSignUpService {

    private final UserJPAGateway userJPAGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long execute(SignUpRequest signUpRequest) throws Exception {
        validateRequest(signUpRequest);
        if (userJPAGateway.existsByEmail(signUpRequest.getEmail())) {
            throw new InvalidRequestException("Email [email: " + signUpRequest.getEmail() + "] is already exist");
        }
        return userJPAGateway.save(constructUser(signUpRequest), 0);
    }

    private User constructUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setIsActive(Constant.ACTIVE_STATUS);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return user;
    }

    private void validateRequest(SignUpRequest signUpRequest) throws InvalidRequestException {
        if (signUpRequest == null) throw new InvalidRequestException("Request is null.");
        if (StringUtils.isNullOrEmpty(signUpRequest.getUserName())) throw new InvalidRequestException("UserName is null or empty.");
        if (StringUtils.isNullOrEmpty(signUpRequest.getPassword())) throw new InvalidRequestException("Password is null or empty.");
        if (StringUtils.isNullOrEmpty(signUpRequest.getConfirmPassword())) throw new InvalidRequestException("Confirm Password is null or empty.");
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) throw new InvalidRequestException("password and password confirm not match.");
        if (StringUtils.isNullOrEmpty(signUpRequest.getEmail())) throw new InvalidRequestException("Email is null or empty");
        if (!StringUtils.isValidEmail(signUpRequest.getEmail())) throw new InvalidRequestException("Email not valid pattern");
    }
}
