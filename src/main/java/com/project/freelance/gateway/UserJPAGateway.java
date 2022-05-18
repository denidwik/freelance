package com.project.freelance.gateway;

import com.project.freelance.entity.User;
import com.project.freelance.util.Paginated;
import com.project.freelance.dto.request.UserListFilterRequest;

public interface UserJPAGateway {
    User findByUserId(long userId);

    boolean existsByEmail(String email);

    Long save(User user, long userId);

    Paginated<User> findAllByFilter(UserListFilterRequest filter);

    boolean existsByUserName(String userName);

    User findUserByEmailAndNotUserId(String email, Long userId);
}
