package com.blog.controller;

import com.blog.dao.RoleRepository;
import com.blog.entity.Role;
import com.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/sys/roleList")
    public String roleList(@PageableDefault(sort = {"roleName"},direction = Sort.Direction.DESC)
                           Pageable pageable, Model model, @Valid Role role){
        Page<Role> roleList = roleService.getRoleList(pageable, role);
        model.addAttribute("roleList",roleList);
        model.addAttribute("roleRt",role);
        return "role/roleList";
    }
}
