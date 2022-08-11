package com.edu.java6assm.service.impl;

import com.edu.java6assm.entity.Role;
import com.edu.java6assm.repository.RoleRepository;

import com.edu.java6assm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repo;


    @Override
    public List<Role> findAll() {
        return repo.findAll();
    }
}
