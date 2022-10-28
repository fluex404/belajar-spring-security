package com.fluex404.learnspringsecurity01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class LearnSpringSecurity01Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringSecurity01Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
