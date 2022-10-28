package com.fluex404.learnspringsecurity01.mapper;

import com.fluex404.learnspringsecurity01.dto.UserRegisterDTO;
import com.fluex404.learnspringsecurity01.entity.UserData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserData toEnttiy(UserRegisterDTO dto) {
        UserData userData = new UserData();
        userData.setUsername(dto.getUsername());
        userData.setPassword(passwordEncoder.encode(dto.getPassword()));
        userData.setEnabled(true);
        return userData;
    }
}
