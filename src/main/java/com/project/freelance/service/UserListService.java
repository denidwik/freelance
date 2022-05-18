package com.project.freelance.service;

import com.project.freelance.controller.user.list.list.UserListResponse;
import com.project.freelance.exception.InvalidRequestException;
import com.project.freelance.dto.request.UserListFilterRequest;

public interface UserListService {
    UserListResponse execute(UserListFilterRequest request) throws InvalidRequestException;
}
