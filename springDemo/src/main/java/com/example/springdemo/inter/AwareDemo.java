package com.example.springdemo.inter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-08-15 10:27
 **/
@Component
public class AwareDemo implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void getOtherClass() {
        ListenerDemo listenerDemo = applicationContext.getBean("ListenerDemo", ListenerDemo.class);
        System.out.println("我是AwareDemo类，我执行了: " + listenerDemo);
    }
}
