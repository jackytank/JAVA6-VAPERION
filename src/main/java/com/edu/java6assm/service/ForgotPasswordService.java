package com.edu.java6assm.service;

import com.edu.java6assm.entity.User;

public interface ForgotPasswordService {
    void updateResetPasswordToken(String token, String email) throws Exception;

    User getByResetPasswordToken(String token);

    void updatePassword(User account, String newPass);

    void sendEmail(String recipientEmail, String link);
}
