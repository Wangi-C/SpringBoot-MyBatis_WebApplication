package org.mybatis.myservice;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request
            , HttpServletResponse response
            , Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object authInfo = session.getAttribute("name");
            if (authInfo != null) {
                return true;
            }
        }
        response.sendRedirect("/login/form");
        return false;
    }
}
