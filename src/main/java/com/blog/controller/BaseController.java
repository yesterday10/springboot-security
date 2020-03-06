package com.blog.controller;

import com.blog.dao.ManagerRepository;
import com.blog.dao.RoleRepository;
import com.blog.dao.UserRepository;
import com.blog.entity.Manager;
import com.blog.entity.Permission;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.security.CustomUserService;
import com.blog.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.security.Security;
import java.util.*;

@Controller
public class BaseController {


    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping(value = {"/","/index"})
    public String index(Model model, Principal principal){
        Authentication authentication = (Authentication) principal;
        System.out.println("登录的用户->"+authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Manager manager = managerRepository.findUserByName(authentication.getName());
        Manager manager = managerRepository.findUserByName(authentication.getName());
        List<Permission> menuList = new ArrayList<>();

        for (Role role : manager.getRoleList()) {
            for (Permission permission : role.getPermissionList()) {
                menuList.add(permission);
            }
        }
        model.addAttribute("menuList", MenuUtil.makeTreeList(menuList));
        model.addAttribute("manager",manager);
        return "index";
    }

//    @PreAuthorize("hasAuthority('role:grant')")
//    @RequestMapping("/alex")
//    public String index1(){
//        return "a";
//    }
//
//    @PreAuthorize("hasRole('manger')")
//    @RequestMapping("/alex2")
//    public String index2(){
//        return "b";
//    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }


}
