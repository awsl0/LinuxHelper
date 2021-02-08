package com.hnumi.config;

import ch.ethz.ssh2.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Connection conn = (Connection) request.getSession().getAttribute("conn");
        if (conn==null) {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        } else {
            return true;
        }
    }
}
