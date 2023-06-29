package com.example.demo.thread;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-29 09:26
 **/

public class JUCDemo {
    public static void main(String[] args) throws Exception {

        new Thread(new ChildRunnable(),"线程1").start();
        Callable<String> 我是runnable的子类 = Executors.callable(new ChildRunnable(), "我是runnable的子类");
        Callable<Object> callable = Executors.callable(new ChildRunnable());
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        //fixedThreadPool.execute(new ChildRunnable());

        //System.out.println(future.get());
        //fixedThreadPool.submit(callable);
        /*Future<?> submit = fixedThreadPool.submit(new ChildRunnable(),"我时runnable子类");
        System.out.println(submit.get());*/
        fixedThreadPool.shutdown();

        //ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //singleThreadExecutor.execute(new ChildRunnable());
     /*   Future<String> future = singleThreadExecutor.submit(new ChildCallable());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        singleThreadExecutor.shutdown();*/
      /*  singleThreadExecutor.submit(callable);
        singleThreadExecutor.submit(new ChildRunnable());
        FutureTask futureTask = new FutureTask<>(new ChildCallable());
        Future<?> submit = singleThreadExecutor.submit(futureTask);
        try {
            futureTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }*/
        System.out.println(Thread.currentThread().getName() + "正在执行");

        //test01();
        //test02();
        //test3();
        test4();
    }



    public static void test01() throws Exception {
        long start = System.currentTimeMillis();
        List<String> platformList = Lists.newArrayList("天猫", "淘宝", "抖音", "京东", "拼多多");
        List<Integer> result = platformList.stream().map(e -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            // 模拟返回每个平台的数据
            int value = RandomUtil.randomInt(100);
            System.out.println(StrUtil.format("[{}] go语言书籍： {}元", e, value));
            return value;
        }).collect(Collectors.toList());
        System.out.println(result);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "毫秒");
    }


    public static void test02() throws Exception {
        long start = System.currentTimeMillis();
        List<String> platformList = Lists.newArrayList("天猫", "淘宝", "抖音", "京东", "拼多多");
        List<Integer> result = platformList.stream()
                .map(e -> CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    // 模拟返回每个平台的数据
                    int value = RandomUtil.randomInt(100);
                    System.out.println(StrUtil.format("[{}] go语言书籍： {}元", e, value));
                    return value;
                })).collect(Collectors.toList()) // 这一步会得到  List<CompletableFuture<Integer>> collect
                .stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "毫秒");
    }


    public static void test3() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("执行业务1...");
            return 1;
        }, threadPool).thenApply(result -> {
            System.out.println("执行业务2...");
            //int a = 1 / 0; // thenApply：如果有异常，任务会中断
            return result + 2;
        }).thenApply(result -> {
            System.out.println("执行业务3...");
            return result + 3;
        }).whenComplete((result, e) -> {
            if (e == null) {
                System.out.println("执行业务最终结果：" + result);
            }
        }).exceptionally(e -> {
            System.out.println("执行业务异常：" + e);
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " 主线程执行完...");
        threadPool.shutdown();
    }

    public static void test4() throws Exception {
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d", 2, TimeUnit.SECONDS));//等待超过两秒就退出
        System.out.println("===================");
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));//等待超过两秒就退出
    }


    static class ChildRunnable implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "正在执行ChildRunnable线程");
            System.out.println("我是Runnable子类，没有返回值，不能抛出异常");
        }
    }

    static class ChildCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "正在执行ChildCallble线程");
            System.out.println("我是callable的子类，有返回值，可以抛出异常");
            return "我是ChildCallable";
        }
    }









}
