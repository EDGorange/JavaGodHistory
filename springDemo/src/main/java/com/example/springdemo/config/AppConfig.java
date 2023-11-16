package com.example.springdemo.config;

import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.interceptor.AOPInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-11-16 14:33
 **/
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Resource
    private AOPInterceptor aopInterceptor;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("AppConfig begin run and registry is {}", new Object[]{JSONObject.toJSONString(registry)});
        registry.addInterceptor(aopInterceptor).addPathPatterns("/**")
                        .excludePathPatterns("/user/login");
    }
}
