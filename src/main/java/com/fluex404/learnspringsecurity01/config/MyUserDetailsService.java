package com.fluex404.learnspringsecurity01.config;

import com.fluex404.learnspringsecurity01.entity.UserData;
import com.fluex404.learnspringsecurity01.repository.UserDataRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserDataRepository userDataRepository;

    public MyUserDetailsService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userdata = userDataRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username tidak ditemukan"));
        return new MyUserDetails(userdata);
    }
}
