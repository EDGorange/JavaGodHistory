package com.example.springdemo.util;

import java.util.concurrent.ThreadFactory;

public class InheritableThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(() -> {
            InheritableMDC.set(InheritableMDC.get()); // 传递MDC上下文信息
            r.run();
            InheritableMDC.clear(); // 清除MDC上下文信息
        });
    }
}
