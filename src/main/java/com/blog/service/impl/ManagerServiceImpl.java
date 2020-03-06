package com.blog.service.impl;

import com.blog.dao.ManagerRepository;
import com.blog.dao.UserRepository;
import com.blog.entity.Manager;
import com.blog.service.ManagerService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Manager> findUserByName(String name) {

       return Optional.ofNullable(managerRepository.findUserByName(name));
    }

    @Override
    public Page<Manager> getManagerList(Pageable pageable, Manager manager) {


        return managerRepository.findAll(ManagerRepository.getManagerList(manager.getUsername(),manager.getState(),manager.getGender(),
                manager.getBeginTime(),manager.getEndTime()),pageable);
    }

    @Override
    public Manager findManagerById(String mangerId) {
        return managerRepository.findById(mangerId).get();
    }


}
