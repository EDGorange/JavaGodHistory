package com.example.springdemo.inter;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-08-15 10:25
 **/
@Component
public class ListenerDemo implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("我是listenerDemo类，我运行了");
    }
}
