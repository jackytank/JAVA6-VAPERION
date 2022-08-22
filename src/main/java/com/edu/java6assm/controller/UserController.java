package com.edu.java6assm.controller;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.service.UserService;
import com.edu.java6assm.utils.CommonUtils;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/account/signup")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "account/signup";
    }

    @GetMapping("/account/editprofile")
    public String editprofile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> loggedinUser = userService.findByUsername(auth.getName());
        model.addAttribute("user", loggedinUser.orElse(new User()));
        return "account/edit_profile";
    }

    @PostMapping("/account/register")
    public String register(Model model, @ModelAttribute User user, HttpServletRequest req) throws MessagingException {
        // System.out.println(user.toString());
        Optional<User> existUserByEmail = userService.findByEmail(user.getEmail());
        Optional<User> existUserByUsername = userService.findByUsername(user.getUsername());
        if (existUserByEmail.isPresent()) {
            model.addAttribute("message", "User with email " + user.getEmail() + " is already registered");
            return "account/signup";
        }
        if (existUserByUsername.isPresent()) {
            model.addAttribute("message", "User with username " + user.getUsername() + " is already registered");
            return "account/signup";
        }
        userService.register(user, CommonUtils.getSiteURL(req));
        model.addAttribute("message", "Please check your email to verify your account");
        return "account/signup";
    }

    @GetMapping("/verify")
    public String verifyAcc(@RequestParam String code) {
        if (userService.verify(code)) {
            return "account/verify-success";
        } else {
            return "account/verify-fail";
        }
    }
}
