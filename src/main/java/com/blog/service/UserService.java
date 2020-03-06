package com.blog.service;

import com.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public Page<User> getUserList(Pageable pageable, User user);

    public User  findByUsernameOrEmail(String username,String email);

}
