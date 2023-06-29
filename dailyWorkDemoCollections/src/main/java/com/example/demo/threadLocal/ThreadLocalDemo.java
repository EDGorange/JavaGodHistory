package com.example.demo.threadLocal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-29 14:23
 **/

public class ThreadLocalDemo {
    static class House {
        //        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
//            @Override
//            protected Integer initialValue() {
//                return 0;
//            }
//        };
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

        public void saleHouse() {
            this.threadLocal.set(this.threadLocal.get() + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*House house = new House();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    int randomNum = RandomUtil.randomInt(10);
                    for (int j = 0; j < randomNum; j++) {
                        house.saleHouse();
                    }
                    System.out.println(Thread.currentThread().getName() + " 卖出: " + house.threadLocal.get() + "RandomNum: " + randomNum);
                } catch (Exception e) {
                    e.getStackTrace();
                } finally {
                    // 在阿里巴巴手册中有说明,尽量在try-finally块进行回收
                    house.threadLocal.remove();
                }
            }, "t" + i).start();
        }*/

        MyThreadLocal.set("3", Thread.currentThread().getName());
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(executorService
                    .submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println(Thread.currentThread().getName() + "正在执行");
                            MyThreadLocal.set("3", Thread.currentThread().getName());
                            return MyThreadLocal.get("3");
                        }
                    }).get());
        }

        executorService.shutdown();

    }


}
