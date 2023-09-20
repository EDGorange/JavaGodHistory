package com.example.demo.thread;

import java.util.concurrent.*;

public class LoopOptimization {
    public static void main(String[] args) {
        int numTasks = 10; // 任务的数量，可以根据实际情况调整
        int process = Runtime.getRuntime().availableProcessors();
        System.out.println("处理器个数为： " + process);
        ExecutorService executorService = Executors.newFixedThreadPool(process); // 创建一个固定大小的线程池
        Future<?>[] futures = new Future<?>[numTasks];

        // 提交任务到线程池，并获取Future对象数组
        for (int i = 0; i < numTasks; i++) {
            futures[i] = executorService.submit(new Task(i));
        }

        // 等待所有任务完成
        for (Future<?> future : futures) {
            try {
                future.get(); // 等待任务完成并获取结果
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("我是主线程,我在 关闭了, 我在线程池关闭之前");
        // 关闭线程池
        executorService.shutdown();
        System.out.println("我是主线程,我在 关闭了, 等待子线程执行完后再关闭");
    }

    static class Task implements Callable<Void> {
        private int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public Void call() throws Exception {
            // 在这里执行具体的任务逻辑，可以根据需要进行优化，例如使用并发数据结构等
            System.out.println("Task " + taskId + " is running.");
            return null;
        }
    }
}