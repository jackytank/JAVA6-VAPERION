package com.edu.java6assm.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserSessionListener implements HttpSessionListener {
    // Source code from
    // https://www.codejava.net/coding/how-to-code-hit-counter-for-java-web-application

    public static final String ONLINE_USERS = "OnlineUsers";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Long onlineUsersCount = 0l;
        Object attributeValue = context.getAttribute(ONLINE_USERS);
        if (attributeValue != null) {
            onlineUsersCount = (Long) attributeValue;
        }

        context.setAttribute(ONLINE_USERS, ++onlineUsersCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();

        Long onlineUsersCount = (Long) context.getAttribute(ONLINE_USERS);

        context.setAttribute(ONLINE_USERS, --onlineUsersCount);
    }
}
