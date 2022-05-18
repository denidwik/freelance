package com.project.freelance.dto.request;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class UserListFilterRequest extends BaseFilter {
    private String userNameContain;
    private String userName;
}
