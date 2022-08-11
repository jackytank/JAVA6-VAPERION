package com.edu.java6assm.service;



import com.edu.java6assm.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> findRolesOfAdministrators();

    List<UserRole> findAll();

    UserRole create(UserRole auth);

    void delete(Integer id);
}
