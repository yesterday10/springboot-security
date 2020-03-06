package com.blog.service.impl;

import com.blog.dao.UserRepository;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;
    
//    @Override
//    public Page<User> getUserList(Pageable pageable, User user) {
//        Specification<User> userList = UserRepository.getUserList(
//                user.getUsername(), user.getNickName(), user.getPhone(), user.getEmail(), user.getState());
//        Page<User> userlist = userRepository.findAll(userList, pageable);
//        return userlist;
//    }

    @Override
    public Page<User> getUserList(Pageable pageable, User user) {
        return null;
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        try {
            User user = userRepository.findByUsernameOrEmail(username, email).get();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
