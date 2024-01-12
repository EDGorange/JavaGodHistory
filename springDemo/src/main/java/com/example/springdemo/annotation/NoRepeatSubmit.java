package com.example.springdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-01-12 16:49
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    // 时间范围 单位秒
    int timeRange() default 1;

    // 限制次数
    int maxCount() default 2;

    // 锁定时间
    int lockingTime() default 10 * 60;
}

