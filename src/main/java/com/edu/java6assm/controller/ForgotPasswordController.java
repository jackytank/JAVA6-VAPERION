package com.edu.java6assm.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.model.AuthProvider;
import com.edu.java6assm.repository.UserRepository;
import com.edu.java6assm.service.ForgotPasswordService;
import com.edu.java6assm.service.MailerService;
import com.edu.java6assm.utils.CommonUtils;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

    @Autowired
    UserRepository repo;

    @Autowired
    MailerService mailer;

    @Autowired
    ForgotPasswordService passService;

    @GetMapping("/account/forgotpassword/form")
    public String form() {
        return "account/forgot_password";
    }

    @PostMapping("/account/forgotpassword")
    public ModelAndView postsend(ModelMap model, HttpServletRequest req) throws MessagingException {
        String email = req.getParameter("forgotEmail");
        String token = RandomString.make(30);
        User user = repo.findByEmail(email).orElse(null);

        if (!repo.existsUserByEmail(email)) {
            model.addAttribute("message", "Email not existed!!");
            // System.out.println("Email not existed!!!");
            return new ModelAndView("account/forgot_password", model);
        }
        if (user.getProvider() != AuthProvider.DATABASE) {
            model.addAttribute("message", "Can't recover account!!");
            // System.out.println("Can't recover account!!");
            return new ModelAndView("account/forgot_password", model);
        }

        try {
            passService.updateResetPasswordToken(token, email);
            String resetPasswordLink = CommonUtils.getSiteURL(req) + "/reset_password?token=" + token;
            passService.sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
            System.out.println("""


                    We have sent a reset password link to your email. Please check.


                    """);
        } catch (Exception e) {
            model.addAttribute("message", "Something went wrong. Please try again later.");
        }
        return new ModelAndView("redirect:/", model);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam("token") String token, ModelMap model) {
        User user = passService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
        }
        return "account/reset_password_form";
    }

    @PostMapping("/reset_password")
    public ModelAndView processResetPassword(HttpServletRequest request, ModelMap model) {
        String token = request.getParameter("token");
        System.out.println(token);
        String password = request.getParameter("password");
        User user = passService.getByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            System.out.println("Invalid Token");
            return new ModelAndView("redirect:/", model);
        } else {
            passService.updatePassword(user, password);
            System.out.println("You have successfully changed your password.");
            model.addAttribute("message", "You have successfully changed your password.");
        }
        return new ModelAndView("redirect:/", model);
    }
}
