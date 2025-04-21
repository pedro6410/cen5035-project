package com.carboncredit.platform.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginChecker implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        String userIdString = (session != null) ? (String) session.getAttribute("userId") : null;

        System.out.println("Requested URI: " + uri);
        System.out.println("Logged in user: " + userIdString);


        if (uri.startsWith("/login") ||
                uri.startsWith("/signup") ||
                uri.startsWith("/signup-success") ||
                uri.startsWith("/static/image/css") ||
                uri.startsWith("/js") ||
                uri.startsWith("/image") ||
                uri.startsWith("/webjars")) {
            return true;
        }


        if (session != null && session.getAttribute("userId") != null) {
            return true;
        }


        response.sendRedirect("/login");
        return false;
    }
}
