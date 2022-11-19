package com.fluex404.learnspringsecurity01.dto;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
