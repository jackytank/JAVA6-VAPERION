package com.edu.java6assm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
    @Query("SELECT DISTINCT a FROM UserRole a WHERE a.user IN ?1")
    List<UserRole> authoritiesOf(List<User> accounts);
}
