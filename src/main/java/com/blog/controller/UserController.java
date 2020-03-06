package com.blog.controller;

import com.blog.dao.UserRepository;
import com.blog.dto.RestCode;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("/ms")
public class UserController {

//    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/userList")
    public ModelMap getUserList(@RequestParam(value="page",defaultValue="1") Integer page,
                                @RequestParam(value="size",defaultValue="10") Integer size, User user){

            //UserRepository.getUserList()
        Page<User> userList = userService.getUserList(PageRequest.of(page - 1, size), user);
        for (User user1 : userList) {
            System.out.println(user1);
        }
        return RestUtil.Success(userList.getTotalElements(),userList.getContent());
    }


}
