package com.fluex404.learnspringsecurity01.controller;

import com.fluex404.learnspringsecurity01.dto.UserRegisterDTO;
import com.fluex404.learnspringsecurity01.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/regis")
    public ResponseEntity regis(@RequestBody UserRegisterDTO dto){
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping("/remove-session")
    public ResponseEntity removeSession(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return ResponseEntity.ok(securityContext);
    }

    @GetMapping("/token")
    public ResponseEntity getToken(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(token);
    }

    @GetMapping("/invalid-token")
    public ResponseEntity getInvalidToken(){
        return ResponseEntity.ok("session kamu habis :(");
    }

}
