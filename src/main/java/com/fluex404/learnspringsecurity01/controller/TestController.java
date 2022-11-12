package com.fluex404.learnspringsecurity01.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {



    @GetMapping("1")
    public ResponseEntity test1(){
        HomeController.value = HomeController.value+1;
        return ResponseEntity.ok(HomeController.value);
    }

    @GetMapping("2")
    public ResponseEntity test2(){
        HomeController.value = HomeController.value+1;
        return ResponseEntity.ok(HomeController.value);
    }
}
