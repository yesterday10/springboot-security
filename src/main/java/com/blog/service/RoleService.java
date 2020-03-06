package com.blog.service;

import com.blog.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    public Page<Role> getRoleList(Pageable pageable, Role role);



}
