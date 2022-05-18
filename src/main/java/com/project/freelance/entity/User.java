package com.project.freelance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class User {
    private long userId;
    private String email;
    private String password;
    private String userName;
    private int isActive;
}
