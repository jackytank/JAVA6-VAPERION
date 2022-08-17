package com.edu.java6assm.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import com.edu.java6assm.listener.UserSessionListener;
import com.edu.java6assm.model.wrapper.CharResponseWrapper;

@WebFilter("*")
public class HitCounterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletContext context = request.getServletContext();

        String realWebAppPath = context.getRealPath("");
        String hitFilePath = realWebAppPath.concat("hit.txt");
        File hitFile = new File(hitFilePath);

        long currentHit = readHitCounterFromFile(hitFile);
        updateHitCounterFile(++currentHit, hitFile);

        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrapper);

        long onlineUsers = (Long) context.getAttribute(UserSessionListener.ONLINE_USERS);

        displayHitCounter(wrapper, response, currentHit, onlineUsers);
    }

    private long readHitCounterFromFile(File hitFile) throws NumberFormatException, IOException {
        if (!hitFile.exists()) {
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(hitFile));) {
            long hit = Long.parseLong(reader.readLine());
            return hit;
        }
    }

    private void updateHitCounterFile(long hit, File hitFile) throws IOException, ServletException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(hitFile));) {
            writer.write(String.valueOf(hit));
        }
    }

    private void displayHitCounter(CharResponseWrapper wrapper, ServletResponse response, long currentHit,
            long onlineUsers) throws IOException {
        PrintWriter writer = response.getWriter();
        if (wrapper.getContentType().contains("text/html")) {
            CharArrayWriter caw = new CharArrayWriter();
            String originalContent = wrapper.toString();
            int indexOfCloseBodyTag = originalContent.indexOf("</body>") - 1;

            caw.write(originalContent.substring(0, indexOfCloseBodyTag));

            String hitCounterContent = "<p>Online Users: " + onlineUsers
                    + " - Pageviews: " + currentHit + "</p>";
            caw.write(hitCounterContent);
            caw.write("\n</body></html>");

            String alteredContent = caw.toString();
            response.setContentLength(alteredContent.length());
            writer.write(alteredContent);

        } else {
            writer.write(wrapper.toString());
        }

        writer.close();
    }
}
