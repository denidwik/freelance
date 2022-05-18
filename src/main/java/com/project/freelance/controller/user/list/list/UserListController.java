package com.project.freelance.controller.user.list.list;

import com.project.freelance.constant.ApiConstant;
import com.project.freelance.exception.InvalidRequestException;
import com.project.freelance.service.UserListService;
import com.project.freelance.service.impl.UserListServiceImpl;
import com.project.freelance.dto.request.UserListFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiConstant.API)
public class UserListController {

    private final UserListService userListService;

    @Autowired
    public UserListController(UserListServiceImpl userService) {
        this.userListService = userService;
    }

    @GetMapping(ApiConstant.LIST_USER)
    public UserListResponse listUser(UserListFilterRequest request) throws InvalidRequestException {
        return userListService.execute(request);
    }
}
