package com.project.freelance.controller.auth.currentuser;

import com.project.freelance.constant.ApiConstant;
import com.project.freelance.security.CurrentUser;
import com.project.freelance.security.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentUserController {

    @GetMapping(ApiConstant.CURRENT_USER)
    public UserSummaryResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return UserSummaryResponse.builder().userName(currentUser.getUsername()).userId(currentUser.getUserId()).email(currentUser.getEmail()).build();
    }
}
