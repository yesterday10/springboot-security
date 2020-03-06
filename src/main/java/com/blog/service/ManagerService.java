package com.blog.service;

import com.blog.entity.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagerService {
    public Optional<Manager> findUserByName(String name);

    public Page<Manager> getManagerList(Pageable pageable,Manager manager);

    public Manager findManagerById(String mangerId);



}
