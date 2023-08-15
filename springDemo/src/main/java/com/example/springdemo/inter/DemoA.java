package com.example.springdemo.inter;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-08-08 10:38
 **/
public interface DemoA {
    public default String getA() {
        return "2";
    }
    public void getString2();
}
