package com.example.springdemo.logAspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 接口日志切面类
 * @author: wang.chengcheng
 * @create: 2023-11-07 16:49
 **/
@Aspect
@Configuration
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //表达式语法：访问修饰符 返回值 包名.包名.包名…类名.方法名(参数列表)
    @Pointcut("execution(* com.example.springdemo.controller..*(..))")
    public void excuteMehtod() {

    };

    @Before("excuteMehtod()")
    public void beforeExecute(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        Object[] args = joinPoint.getArgs();
        logger.info("当前请求的接口URL为{}, 当前的方法类型为{}, 当前的参数为{}", new Object[]{requestURI, method, JSONObject.toJSONString(args)});
    }

    @AfterReturning(value = "excuteMehtod()", returning = "rtv")
    public void after(JoinPoint point, Object rtv) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        logger.info("请求结束, method: {}, uri: {}, params: {}", new Object[] {method, uri, JSONObject.toJSONString(rtv)});
    }
}
