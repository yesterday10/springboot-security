package com.blog.security;

import com.blog.dao.ManagerRepository;
import com.blog.dao.UserRepository;
import com.blog.entity.Manager;
import com.blog.entity.Permission;
import com.blog.entity.Role;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ManagerDetailService implements UserDetailsService {

    @Autowired
    ManagerRepository managerRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerRepository.findUserByName(username);
        if (manager==null){
            new UsernameNotFoundException("用户不存在");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Role role : manager.getRoleList()) {
               authority = new SimpleGrantedAuthority(role.getRoleCode());
               authorities.add(authority);
            for (Permission permission : role.getPermissionList()) {
                authority = new SimpleGrantedAuthority(permission.getPurview());
                authorities.add(authority);
            }
        }

        return new org.springframework.security.core.userdetails.User(manager.getUsername(),manager.getPassword(),authorities);
    }
}
