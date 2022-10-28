package com.fluex404.learnspringsecurity01.config;

import com.fluex404.learnspringsecurity01.repository.UserDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDataRepository userDataRepository;

    public AppSecurityConfig(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/v1/admin").hasAnyRole("ADMIN")
                .antMatchers("/api/v1/user").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin().disable()
                .httpBasic()
                .and()
                .sessionManagement(session -> session.invalidSessionUrl("/invalid-token"));
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new MyUserDetailsService(userDataRepository));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
