package com.edu.java6assm.service.impl;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.java6assm.entity.Role;
import com.edu.java6assm.entity.User;
import com.edu.java6assm.entity.UserRole;
import com.edu.java6assm.model.AuthProvider;
import com.edu.java6assm.model.MailInfo;
import com.edu.java6assm.repository.RoleRepository;
import com.edu.java6assm.repository.UserRepository;
import com.edu.java6assm.repository.UserRoleRepository;
import com.edu.java6assm.service.MailerService;
import com.edu.java6assm.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRoleRepository userRoleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MailerService mailer;

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getAdministators() {
        return userRepo.getAdministrators();
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void processOAuthPostLogin(String username, String email, String image, String oauth2ClientName) {
        Optional<User> existAcc = userRepo.findByEmail(email);
        if (!existAcc.isPresent()) {
            User newAcc = new User();
            AuthProvider authProvider = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
            newAcc.setUsername(username);
            newAcc.setEmail(email);
            newAcc.setProvider(authProvider);
            newAcc.setImage_url(image);
            newAcc.setEnabled(true);
            System.out.println(newAcc.toString());
            userRepo.save(newAcc);
        }
    }

    @Override
    public void updateAuthenticationTypeOAuth(String username, String oauth2ClientName) {
        AuthProvider authProvider = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
        userRepo.updateAuthenticationTypeOAuth(username, authProvider);
    }

    @Override
    public void updateAuthenticationTypeDB(String username, String oauth2ClientName) {
        AuthProvider authProvider = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
        userRepo.updateAuthenticationTypeDB(username, authProvider);
    }

    @Override
    public void register(User user, String url) throws MessagingException {
        // save user
        String encodedPassword = encoder.encode(user.getPassword());
        String randomCode = RandomString.make(64);
        user.setPassword(encodedPassword);
        user.setVerify_code(randomCode);
        user.setEnabled(false);
        user.setProvider(AuthProvider.DATABASE);
        User savedUser = userRepo.save(user);

        // set role CUST (Customer) to user vì nếu là người dùng bình thường đăng ký thì
        // chỉ set
        // role là CUST
        Optional<Role> role = roleRepo.findById("CUST");
        userRoleRepo.save(new UserRole(user, role.get()));
        sendVerifyEmail(user, url);

    }

    @Override
    public void sendVerifyEmail(User user, String url) throws MessagingException {
        MailInfo mail = new MailInfo();
        mail.setTo(user.getEmail());
        mail.setSubject("TXT Vaperium - Verify your email");
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>";

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = url + "/verify?code=" + user.getVerify_code();
        content = content.replace("[[URL]]", verifyURL);
        mail.setBody(content);
        mailer.queue(mail);
    }

    @Override
    public User save(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> findUser = userRepo.findByUsername(user.getUsername());
        if (findUser.isPresent()) {
            if (encoder.matches(user.getPassword(), findUser.get().getPassword())) {
                user.setPassword(encoder.encode(user.getPassword()));
            } else if (!encoder.matches(user.getPassword(), findUser.get().getPassword())) {
                user.setPassword(encoder.encode(user.getPassword()));
            } else {

            }
        }
        return userRepo.save(user);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepo.deleteByUsername(username);
    }

    @Override
    public boolean verify(String verifyCode) {
        User user = userRepo.findByVerifyCode(verifyCode);
        if (user == null || user.getEnabled()) {
            return false;
        } else {
            user.setVerify_code("0");
            user.setEnabled(true);
            userRepo.save(user);
            return true;
        }
    }

}
