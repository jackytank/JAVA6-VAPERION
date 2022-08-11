package com.edu.java6assm.service.impl;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.entity.UserRole;
import com.edu.java6assm.repository.UserRepository;
import com.edu.java6assm.repository.UserRoleRepository;
import com.edu.java6assm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepo;

    @Autowired
    UserRepository userRepo;


    public List<UserRole> findRolesOfAdministrators() {
        List<User> accounts = userRepo.getAdministrators();
        return userRoleRepo.authoritiesOf(accounts);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole create(UserRole auth) {
        return userRoleRepo.save(auth);
    }

    @Override
    public void delete(Integer id) {
        userRoleRepo.deleteById(id);
    }
}
