package com.blog.security;

import com.blog.dao.ManagerRepository;
import com.blog.dao.UserRepository;
import com.blog.entity.Permission;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.service.ManagerService;
import com.blog.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public class CustomUserService implements UserDetailsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomUserService.class);


    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        User user = userService.findByUsernameOrEmail(username, username);
        if (user!=null){
            logger.debug("登录成功");
        }

        SimpleGrantedAuthority authority;

        for (Role role : user.getRoleList()) {
            //  要是用hasRole前缀要加ROLE_   注解使用@PreAuthorize("hasRole('manger')")
            authority = new SimpleGrantedAuthority("ROLE_"+role.getRoleCode());

            authorities.add(authority);
            for (Permission permission: role.getPermissionList()){
                authority = new SimpleGrantedAuthority(permission.getPurview());
                authorities.add(authority);
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
