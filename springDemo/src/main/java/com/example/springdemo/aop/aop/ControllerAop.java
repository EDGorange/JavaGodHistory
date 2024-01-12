package com.example.springdemo.aop.aop;


import com.example.springdemo.annotation.NoRepeatSubmit;
import com.example.springdemo.inter.RedisKey;
import com.example.springdemo.util.DateUtil;
import com.example.springdemo.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author： ruilinxin
 * @date： 2020/6/4 14:01
 * @description：防止重复提交
 */
@Aspect
@Component
public class ControllerAop {

    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAop.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("execution(public * com.example.springdemo..*.controller..*(..))")
    public void noRepeatSubmitPointcut() {
    }
    @Before("noRepeatSubmitPointcut()")
    public void before(final JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
    }

    @Before("noRepeatSubmitPointcut() && @annotation(noRepeatSubmit)")
    public void before_noRepeatSubmit(final JoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder sb = new StringBuilder(RedisKey.NO_REPEAT_SUBMIT);
        sb.append(request.getServletPath());
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            sb.append(token);
        } else {
            return;
        }
        String key = sb.toString();
        Integer maxCount = noRepeatSubmit.maxCount();
        Integer timeRange = noRepeatSubmit.timeRange();
        Integer lockingTime = noRepeatSubmit.lockingTime();
        LOGGER.debug("+++++++++++++++++防刷Key:{}", key);
        // 先加1
        long count = redisUtil.incr(key, 1);

        if (count > maxCount) {
            // 超出访问次数
            redisUtil.expire(key, lockingTime);
            LOGGER.debug("=================启动接口--{}---防刷=============", methodName);
            startTime.remove();
            //throw  BusinessException.generate("刷接口", ResponseCodeEnum.DUPLICATION);
        } else {
            // 没有则设置失效时间
            redisUtil.expire(key, timeRange);
        }


    }

    @AfterReturning(pointcut = "noRepeatSubmitPointcut()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
        if (startTime.get() == null) {
            return;
        }
        String name = joinPoint.getSignature().getName();
        long diffMs = DateUtil.elapsedTimeMillis(startTime.get());
        // 接口相应超过1秒的报error
        if (diffMs > 1 * 1000L) {
            LOGGER.error("-->-->-->-->-->ERROR-->-->-->-->-->{}接口相应过慢，处理耗时：{}毫秒", name, diffMs);
        } else {
            LOGGER.debug("-->-->-->-->{}接口处理耗时：{}毫秒", name, diffMs);
        }
        startTime.remove();
    }

}
