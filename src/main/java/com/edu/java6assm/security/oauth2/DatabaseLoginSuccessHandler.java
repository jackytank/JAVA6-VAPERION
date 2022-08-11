package com.edu.java6assm.security.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.edu.java6assm.security.CustomUserDetails;
import com.edu.java6assm.service.UserService;



@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    @Autowired UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        userService.updateAuthenticationTypeDB(userDetails.getUsername(), "database");
        getRedirectStrategy().sendRedirect(request, response, "/security/login/success");
    }
}
