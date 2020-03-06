package com.blog.service.impl;

import com.blog.dao.RoleRepository;
import com.blog.entity.Role;
import com.blog.service.RoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> getRoleList(Pageable pageable, Role role) {
        Specification<Role> roleList = RoleRepository.getRoleList(role.getRoleName(), role.getAvailable(), role.getRoleType());
        Page<Role> roles = roleRepository.findAll(roleList, pageable);
        return roles;
    }
}
