package com.example.springdemo.config;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

/**
 * @author: wuxinx
 * @date: 2022/2/14 ,23:18
 * @version: v1.0
 * @description: 登录拦截器，对请求进行 Token 校验拦截
 **/
@Slf4j
public class LoginAuthenticationFilter implements Filter {

    public static Set<String> excludesPattern;

    private String contextPath;

    public static String loginUrl;

    private final String TRACE_ID = "trace_id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        String uuid = UUID.randomUUID().toString();
        MDC.put(TRACE_ID, uuid);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String requestURI = request.getRequestURI();
        final boolean exclusion = isExclusion(excludesPattern, contextPath, requestURI);

        // 路径忽略
        if (exclusion) {
            if(log.isDebugEnabled()) {
                log.debug("===登录拦截，当前路径在忽略范围内:{}", requestURI);
            }
        } else {
            /*String tokenFromCookie = getTokenFromCookie(request);
            if (StringUtils.isEmpty(tokenFromCookie)) {
                final ResultResponse<Object> result = ResultResponse.errorResult(RestResponseCode.TOKEN_ISNULL.getCode(), RestResponseCode.TOKEN_ISNULL.getDescription());
                final Map<Object, Object> map = new HashMap<>();
                map.put("loginUrl", loginUrl);
                result.setData(map);
                com.aliyun.citybrain.common.utils.StringUtils.printJsonInfo(result, response);
                return;
            }
            if(log.isDebugEnabled()) {
                log.debug("token:{}", tokenFromCookie);
            }
            CacheService cacheUtil = SpringContextHolder.getBean(CacheService.class);
            Object userId = cacheUtil.get(tokenFromCookie);
            // 登录失败，返回错误信息
            if (userId == null) {
                final ResultResponse<Object> result = ResultResponse.errorResult(RestResponseCode.TOKEN_NO_EXIT.getCode(), RestResponseCode.TOKEN_NO_EXIT.getDescription());
                final Map<Object, Object> map = new HashMap<>();
                map.put("loginUrl", loginUrl);
                result.setData(map);
                com.aliyun.citybrain.common.utils.StringUtils.printJsonInfo(result, response);
                return;
            }


            // 登录成功缓存续租 2小时
            cacheUtil.set(tokenFromCookie, userId, SystemConstant.TWO_HOUR_CAHCE_TIME);
            SessionUtil.bindTokenSubject(tokenFromCookie, Long.valueOf(userId.toString()));*/
        }
        filterChain.doFilter(request, response);
        MDC.remove(TRACE_ID);
    }

    private boolean isExclusion(Set<String> excludesPattern, String contextPath, String requestURI) {
        return false;
    }

/*    private String getTokenFromCookie(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equalsIgnoreCase(SystemConstant.TOKEN_KEY)) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }*/
}
