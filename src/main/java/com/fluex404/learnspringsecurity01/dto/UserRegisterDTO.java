package com.fluex404.learnspringsecurity01.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String roles;
}
