package com.edu.java6assm.service;

import javax.mail.MessagingException;

import com.edu.java6assm.model.MailInfo;

public interface MailerService {
    // void send(MailInfo mail)throws MessagingException;
    // void send(String to, String subject, String body) throws MessagingException;
    void send(MailInfo mail) throws MessagingException;

    void send(String to, String subject, String body) throws MessagingException;

    void queue(MailInfo mail);

    void queue(String to, String subject, String body);

}
