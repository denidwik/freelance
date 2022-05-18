package com.project.freelance.controller.user.list.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class UserListResponse {
    private List<User> users;
    private long totalRecord;
    private int totalPage;

    @Data @Builder
    @AllArgsConstructor @NoArgsConstructor
    public static class User {
        private Long userId;
        private String userName;
        private String email;
    }
}
