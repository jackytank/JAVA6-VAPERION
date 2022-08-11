package com.edu.java6assm.security.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.edu.java6assm.service.UserService;


@Component
public class OAuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
        String oauth2ClientName = oauth2User.getOauth2ClientName();
        String username = oauth2User.getName();
        String email = oauth2User.getEmail();
        String image = (String) oauth2User.getAttributes().get("picture");

        userService.processOAuthPostLogin(username, email, image, oauth2ClientName);
        userService.updateAuthenticationTypeOAuth(email, oauth2ClientName);
        getRedirectStrategy().sendRedirect(request, response, "/security/login/success");
    }
}
