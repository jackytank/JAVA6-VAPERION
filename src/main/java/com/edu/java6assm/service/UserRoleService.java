package com.edu.java6assm.service;



import java.util.List;

import com.edu.java6assm.entity.UserRole;
import com.edu.java6assm.model.UserRoleCount;

public interface UserRoleService {
    List<UserRole> findRolesOfAdministrators();

    List<UserRoleCount> getUserRoleCounts();

    List<UserRole> findAll();

    UserRole create(UserRole auth);

    void delete(Integer id);
}
