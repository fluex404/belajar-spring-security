package com.fluex404.learnspringsecurity01.service;

import com.fluex404.learnspringsecurity01.config.MyUserDetails;
import com.fluex404.learnspringsecurity01.config.MyUserDetailsService;
import com.fluex404.learnspringsecurity01.dto.UserLoginRequestDTO;
import com.fluex404.learnspringsecurity01.dto.UserLoginResponseDTO;
import com.fluex404.learnspringsecurity01.dto.UserRegisterDTO;
import com.fluex404.learnspringsecurity01.entity.Role;
import com.fluex404.learnspringsecurity01.entity.UserData;
import com.fluex404.learnspringsecurity01.entity.UserRole;
import com.fluex404.learnspringsecurity01.mapper.UserMapper;
import com.fluex404.learnspringsecurity01.repository.RoleRepository;
import com.fluex404.learnspringsecurity01.repository.UserDataRepository;
import com.fluex404.learnspringsecurity01.repository.UserRoleRepository;
import com.fluex404.learnspringsecurity01.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDataRepository userDataRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;

    @Transactional
    public String register(UserRegisterDTO dto){
        UserData userData = userDataRepository.save(userMapper.toEnttiy(dto));
        for (String roleDTO : dto.getRoles().split(",")) {
            Role role = roleRepository.save(roleRepository.findByAuthority(roleDTO).orElse(new Role(roleDTO)));
            userRoleRepository.save(new UserRole(userData, role));
        }
        return "success";
    }

    public UserLoginResponseDTO login(UserLoginRequestDTO dto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        }catch (Exception e){
            throw new Exception("Username dan password salah");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(dto.getUsername());
        return new UserLoginResponseDTO(jwtUtil.generateToken(userDetails));
    }

}
