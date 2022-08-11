package com.edu.java6assm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.exception.ResourceNotFoundException;
import com.edu.java6assm.model.MailInfo;
import com.edu.java6assm.repository.UserRepository;
import com.edu.java6assm.service.ForgotPasswordService;
import com.edu.java6assm.service.MailerService;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    UserRepository repo;

    @Autowired
    MailerService mailerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void updateResetPasswordToken(String token, String email) throws Exception {
        User user = repo.findByEmail(email).orElse(null);
        if (user != null) {
            user.setReset_pwd_token(token);
            repo.save(user);
        } else {
            throw new ResourceNotFoundException("User", "Email", email);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return repo.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPass) {
        String encodedPass = passwordEncoder.encode(newPass);
        user.setPassword(encodedPass);
        user.setReset_pwd_token(null);
        repo.save(user);

    }

    @Override
    public void sendEmail(String recipientEmail, String link) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setTo(recipientEmail);
        mailInfo.setSubject("PaperMoneyStore - Reset password");
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        mailInfo.setBody(content);
        mailerService.queue(mailInfo);

    }

}