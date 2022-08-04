package com.edu.java6assm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.java6assm.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
    
}
