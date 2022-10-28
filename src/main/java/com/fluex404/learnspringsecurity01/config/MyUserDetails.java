package com.fluex404.learnspringsecurity01.config;

import com.fluex404.learnspringsecurity01.entity.UserData;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private Boolean enabled;
    private List<String> roles = new ArrayList<>();

    public MyUserDetails(String username, String password, Boolean enabled, List<String> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public MyUserDetails(UserData userData){
        this.username = userData.getUsername();
        this.password = userData.getPassword();
        this.enabled = userData.getEnabled();
        this.roles = userData.getUserRoles().stream().map(userRole -> userRole.getRole().getAuthority()).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
