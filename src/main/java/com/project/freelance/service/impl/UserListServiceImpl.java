package com.project.freelance.service.impl;

import com.project.freelance.controller.user.list.list.UserListResponse;
import com.project.freelance.entity.User;
import com.project.freelance.exception.InvalidRequestException;
import com.project.freelance.gateway.UserJPAGateway;
import com.project.freelance.service.UserListService;
import com.project.freelance.util.Paginated;
import com.project.freelance.dto.request.UserListFilterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

    private final UserJPAGateway userJPAGateway;

    @Override
    public UserListResponse execute(UserListFilterRequest filter) throws InvalidRequestException {
        validationRequest(filter);
        Paginated<User> users = userJPAGateway.findAllByFilter(filter);
        UserListResponse response = new UserListResponse();
        if(users != null && users.results != null) {
            response.setUsers(new ArrayList<>());
            for (User user : users.getResults()) {
                UserListResponse.User userResponse = UserListResponse.User.builder()
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .build();
                response.getUsers().add(userResponse);
            }
            response.setTotalPage(users.getTotalPage());
            response.setTotalRecord(users.getTotalRecord());
        }
        return response;
    }


    private void validationRequest(UserListFilterRequest filter) throws InvalidRequestException {
        if (filter == null) throw new InvalidRequestException("filter request is null.");
    }
}
