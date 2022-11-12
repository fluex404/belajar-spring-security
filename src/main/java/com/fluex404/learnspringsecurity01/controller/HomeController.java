package com.fluex404.learnspringsecurity01.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    public static Integer value = 0;

    @GetMapping("/user")
    public ModelMap getUser(HttpSession session){
        session.setAttribute("KUDA", "kudakuakuda");

        ModelMap m = new ModelMap();
        m.put("data", "ini data user");
        m.put("message", "ini message user");
        m.put("status", 200);
        return m;
    }

    @GetMapping("/admin")
    public ModelMap getAdmin(){
        ModelMap m = new ModelMap();
        m.put("data", "ini data admin");
        m.put("message", "ini message admin");
        m.put("status", 200);
        return m;
    }
}
