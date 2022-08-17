package com.edu.java6assm.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import com.edu.java6assm.entity.User;

public interface UserService {

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);

    User update(User user);

    void deleteByUsername(String id);

    List<User> findAll();

    List<User> getAdministators();

    void register(User user, String url) throws MessagingException;

    void sendVerifyEmail(User user, String url) throws MessagingException;

    boolean verify(String verifyCode);

    void processOAuthPostLogin(String username, String email, String image, String oauth2ClientName);

    void updateAuthenticationTypeOAuth(String username, String oauth2ClientName);

    void updateAuthenticationTypeDB(String username, String oauth2ClientName);
}
