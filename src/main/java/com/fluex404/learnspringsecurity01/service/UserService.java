package com.fluex404.learnspringsecurity01.service;

import com.fluex404.learnspringsecurity01.dto.UserRegisterDTO;
import com.fluex404.learnspringsecurity01.entity.Role;
import com.fluex404.learnspringsecurity01.entity.UserData;
import com.fluex404.learnspringsecurity01.entity.UserRole;
import com.fluex404.learnspringsecurity01.mapper.UserMapper;
import com.fluex404.learnspringsecurity01.repository.RoleRepository;
import com.fluex404.learnspringsecurity01.repository.UserDataRepository;
import com.fluex404.learnspringsecurity01.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserDataRepository userDataRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserDataRepository userDataRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userDataRepository = userDataRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public String register(UserRegisterDTO dto){
        UserData userData = userDataRepository.save(userMapper.toEnttiy(dto));
        for (String roleDTO : dto.getRoles().split(",")) {
            Role role = roleRepository.save(roleRepository.findByAuthority(roleDTO).orElse(new Role(roleDTO)));
            userRoleRepository.save(new UserRole(userData, role));
        }
        return "success";
    }

}
