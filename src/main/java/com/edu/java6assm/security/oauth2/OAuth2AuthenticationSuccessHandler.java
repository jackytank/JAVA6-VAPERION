package com.edu.java6assm.security.oauth2;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.edu.java6assm.exception.BadRequestException;
import com.edu.java6assm.service.UserService;
import com.edu.java6assm.utils.CookieUtils;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    UserService userService;

    // @Override
    // public void onAuthenticationSuccess(HttpServletRequest request,
    // HttpServletResponse response,
    // Authentication authentication) throws IOException, ServletException {
    // CustomOAuth2User oauth2User = (CustomOAuth2User)
    // authentication.getPrincipal();
    // String oauth2ClientName = oauth2User.getOauth2ClientName();
    // String username = oauth2User.getName();
    // String email = oauth2User.getEmail();
    // String image = (String) oauth2User.getAttributes().get("picture");

    // userService.processOAuthPostLogin(username, email, image, oauth2ClientName);
    // userService.updateAuthenticationTypeOAuth(email, oauth2ClientName);
    // getRedirectStrategy().sendRedirect(request, response, "/auth/login/success");
    // }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
