package com.edu.java6assm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.java6assm.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
