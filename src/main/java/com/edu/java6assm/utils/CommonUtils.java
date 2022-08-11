package com.edu.java6assm.utils;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
    public static String getSiteURL(HttpServletRequest req) {
        String siteURL = req.getRequestURL().toString();
        return siteURL.replace(req.getServletPath(), "");
    }
}
