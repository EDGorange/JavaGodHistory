package com.example.springdemo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-11-16 14:28
 **/
@Component
public class AOPInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("AOPInterceptor begin run");
        /*HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userInfo") != null) {
            return true;
        }

        //response.sendRedirect("/login.html");
        response.sendError(500);
        return false;*/
        return true;
    }

}
