package com.edu.java6assm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.model.AuthProvider;
import com.edu.java6assm.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String username, String email, String image, String oauth2ClientName) {
        User existAcc = repo.findByEmail(email).get();
        if (existAcc == null) {
            User newAcc = new User();
            AuthProvider authProvider = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
            newAcc.setUsername(username);
            newAcc.setEmail(email);
            newAcc.setProvider(authProvider);
            newAcc.setImage_url(image);
            repo.save(newAcc);
        }
    }

    public void updateAuthenticationTypeOAuth(String username, String oauth2ClientName) {
        AuthProvider authProvider = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
        repo.updateAuthenticationTypeOAuth(username, authProvider);
    }

    public void updateAuthenticationTypeDB(String username, String oauth2ClientName) {
        AuthProvider authProvider = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
        repo.updateAuthenticationTypeDB(username, authProvider);
    }
}
