package com.project.freelance.service.impl;

import com.project.freelance.controller.auth.login.JwtAuthenticationResponse;
import com.project.freelance.controller.auth.login.LoginRequest;
import com.project.freelance.exception.InvalidRequestException;
import com.project.freelance.security.JwtTokenProvider;
import com.project.freelance.security.UserPrincipal;
import com.project.freelance.service.AuthLoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthLoginServiceImpl implements AuthLoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public JwtAuthenticationResponse execute(LoginRequest loginRequest) throws InvalidRequestException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        System.out.println("masuk sinbi");

        if (authentication == null)
            throw new InvalidRequestException("username or password incorrect");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwt);
        log.info("User with [email: {}] has logged in", userPrincipal.getEmail());
        return jwtAuthenticationResponse;
    }
}
