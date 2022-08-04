package com.edu.java6assm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.model.AuthProvider;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.user_name = ?1")
    public Optional<User> findByUser_name(String user_name);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.provider = ?2 WHERE u.email = ?1")
    public void updateAuthenticationTypeOAuth(String email, AuthProvider provider);

    @Modifying
    @Query("UPDATE User u SET u.provider = ?2 WHERE u.user_name = ?1")
    public void updateAuthenticationTypeDB(String user_name, AuthProvider provider);
}
