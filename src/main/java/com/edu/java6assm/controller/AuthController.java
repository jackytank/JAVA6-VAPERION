package com.edu.java6assm.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping("/auth/login/form")
    public String form() {
        return "user/login";
    }

    @RequestMapping("/auth/login/success")
    public String success(ModelMap model) {
        model.addAttribute("message", "Login success!");
        return "forward:/auth/login/form";
    }

    @RequestMapping("/auth/login/error")
    public String error(ModelMap model) {
        model.addAttribute("message", "Wrong user credential!");
        return "forward:/auth/login/form";
    }

    @RequestMapping("/auth/logoff/success")
    public String logoff(ModelMap model) {
        model.addAttribute("message", "Sign out success!");
        return "forward:/auth/login/form";
    }

    // OAuth2

    @RequestMapping("/oauth2/login/success")
    public String success(OAuth2AuthenticationToken oToken) {
        return "forward:/auth/login/success";
    }
}
