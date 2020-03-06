package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

//    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(){

//        logger.debug("进入登录页面");

        return "login";
    }

//    @PostMapping("/login")
//    public String logins(String username,String password){
//        System.out.println(username+"\t"+password);
//        return "/index";
//    }
}
