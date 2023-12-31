package com.example.demo.threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-29 15:03
 **/

public final class MyThreadLocal {
    private MyThreadLocal() {

    }

    private static final   ThreadLocal<Map<String, String>> myThreadLocal = new InheritableThreadLocal<Map<String, String>>() {
        @Override
        protected Map<String, String> initialValue() {
            System.out.println("我是final的ThreadLocal的初始类");
            return new HashMap<>();
        }
    };


    public static void set(String key, String value) {
        Map<String, String> stringStringMap = myThreadLocal.get();
        if (null != stringStringMap) {
            stringStringMap.put(key, value);
        }
    }

    public static String get(String key) {
        Map<String, String> stringStringMap = myThreadLocal.get();
        if (null != stringStringMap) {
           return stringStringMap.get(key);
        }
        return null;
    }

    private void testDemo() {
        myThreadLocal.get();
        //myThreadLocal = new ThreadLocal<>();
    }
}
