package com.carboncredit.platform.dto;


import com.carboncredit.platform.model.User;
import lombok.Getter;

@Getter
public class UserWithRole {
    private final User user;
    private final String role;
    private final String userName;

    public UserWithRole(User user, String role, String userName) {
        this.user = user;
        this.role = role;
        this.userName = userName;
    }
}
