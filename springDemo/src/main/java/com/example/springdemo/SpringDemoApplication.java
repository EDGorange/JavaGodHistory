package com.example.springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springdemo.dao")
public class SpringDemoApplication {

    public static void main(String[] args) {
        //System.out.println("我是main方法，我在springapplication之前执行");
        SpringApplication.run(SpringDemoApplication.class, args);
        //System.out.println("我是main方法，我在springapplication之后执行");
    }

}
