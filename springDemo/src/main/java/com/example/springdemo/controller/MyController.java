package com.example.springdemo.controller;

import com.example.springdemo.util.InheritableThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadFactory;

@RestController
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @GetMapping("/run-task")
    public String runTask() {
        logger.info("Starting a new task...");

        // 创建线程并使用自定义的InheritableThreadFactory
        ThreadFactory threadFactory = new InheritableThreadFactory();
        Thread thread = threadFactory.newThread(() -> {
            logger.info("Logging from thread: " + Thread.currentThread().getName());
        });
        thread.start();

        return "Task started. Check logs for details.";
    }
}
