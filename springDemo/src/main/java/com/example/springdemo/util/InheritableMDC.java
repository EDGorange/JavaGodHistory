package com.example.springdemo.util;

import org.slf4j.MDC;

public class InheritableMDC {

    private static final InheritableThreadLocal<String> context = new InheritableThreadLocal<>();

    public static void set(String value) {
        context.set(value);
        MDC.put("username", value); // 设置MDC上下文信息
    }

    public static String get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
        MDC.clear(); // 清除MDC上下文信息
    }
}
