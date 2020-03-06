package com.blog;

import com.blog.dao.PermissionRepository;
import com.blog.dao.RoleRepository;
import com.blog.dao.UserRepository;
import com.blog.entity.Manager;
import com.blog.entity.Permission;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import spock.mock.AutoAttach;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

//    @Test
//    public void test1() {
////        Boolean aBoolean = userRepository.existsByEmail("53123775@qq.com");
////        System.out.println(aBoolean);
//        Optional<User> byId = userRepository.findById("40289f946508da04016508db5d1d0000");
////        for (Role role : byId.get().getRoleList()) {
////            System.out.println(role);
////        }s
//        Set<Role> roleList = byId.get().getRoleList();
//        for (Role role : roleList) {
//            System.out.println(role.getId());
//        }
//    }
//
//    @Test
//    public void test2(){
//        User user = userService.findByUsernameOrEmail("hehe", "5858585858");
//        System.out.println(user.getRoleList().toString());
//
//    }

    @Test
    public void test3(){
//        Optional<User> byUsernameOrEmail = userRepository.findByUsernameOrEmail("hehe", "hehe");
//        User user = byUsernameOrEmail.get();
////        Specification<User> userList = UserRepository.getUserList(user.getUsername(), user.getNickName(), user.getPhone(), user.getEmail(), user.getState());
////       RoleRepository.getRoleList()
//        List list = new ArrayList();
//        Set<Role> roleList = user.getRoleList();
//        for (Role role : roleList) {
//            Set<Permission> permissionList = role.getPermissionList();
//            for (Permission permission : permissionList) {
//                System.out.println(permission.getPurview()+"****");
//            }
//        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        User user = userService.findByUsernameOrEmail("hehe", "hehe");

        SimpleGrantedAuthority authority;

        for (Role role : user.getRoleList()) {
            authority = new SimpleGrantedAuthority(role.getRoleCode());
            authorities.add(authority);
            for (Permission permission: role.getPermissionList()){
                authority = new SimpleGrantedAuthority(permission.getPurview());
                authorities.add(authority);
            }
        }
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println(grantedAuthority);
        }
    }

    @Test
    public void test4(){
        Optional<User> byUsernameOrEmail = userRepository.findByUsernameOrEmail("hehe", "hehe");
        Set<Role> roleList = byUsernameOrEmail.get().getRoleList();
        for (Role role : roleList) {
            for (Manager manager : role.getManagers()) {
                System.out.println(manager.toString());
            }
        }


    }

}
