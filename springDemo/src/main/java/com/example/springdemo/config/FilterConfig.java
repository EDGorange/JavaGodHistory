package com.example.springdemo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-11-28 17:59
 **/

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoginAuthenticationFilter> loginAuthenticationFilterRegistration() {
        FilterRegistrationBean<LoginAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginAuthenticationFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LoginAuthenticationFilter");
        return registrationBean;
    }
}
