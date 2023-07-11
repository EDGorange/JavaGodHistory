package com.example.demo.concurrentMap;

import cn.hutool.core.util.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-07-11 16:34
 **/

public class ConCurrentMapDemo implements Runnable{

    private static final Map<String, Integer> map = new HashMap<>();
    private static final ConcurrentMap<String, Integer> concur = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ConCurrentMapDemo conCurrentMapDemo = new ConCurrentMapDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executorService.execute(conCurrentMapDemo);
        }

        executorService.shutdown();
        System.out.println(map.size());

    }


    @Override
    public void run() {
        map.put("1" + Thread.currentThread().getName(), RandomUtil.randomInt());
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("run");
    }
}
