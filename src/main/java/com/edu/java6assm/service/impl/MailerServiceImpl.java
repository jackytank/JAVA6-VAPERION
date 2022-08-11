package com.edu.java6assm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.edu.java6assm.model.MailInfo;
import com.edu.java6assm.service.MailerService;

@Service
public class MailerServiceImpl implements MailerService {
    @Autowired
    JavaMailSender sender;

    List<MailInfo> list = new ArrayList<>();

    @Override
    public void send(MailInfo mail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);
        helper.setReplyTo(mail.getFrom());
        String[] cc = mail.getCc();
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }
        String[] bcc = mail.getBcc();
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        List<File> files = mail.getFiles();
        if (files.size() > 0) {
            for (File file : files) {
                helper.addAttachment(file.getName(), file);
            }
        }
        sender.send(message);
    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        this.send(new MailInfo(to, subject, body));
    }

    @Override
    public void queue(MailInfo mail) {
        list.add(mail);
    }

    @Override
    public void queue(String to, String subject, String body) {
        queue(new MailInfo(to, subject, body));
    }

    @Scheduled(fixedDelay = 2000)
    public void run() {
        while (!list.isEmpty()) {
            MailInfo mail = list.remove(0);
            try {
                this.send(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
