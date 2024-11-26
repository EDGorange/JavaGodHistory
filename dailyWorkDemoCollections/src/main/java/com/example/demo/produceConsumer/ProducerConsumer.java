package com.example.demo.produceConsumer;

import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_CAPACITY = 5;

    // 生产者线程
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (queue.size() == MAX_CAPACITY) {
                    wait(); // 队列满时，生产者等待
                }
                System.out.println("Produced: " + value);
                queue.add(value++); // 生产数据
                notify(); // 通知消费者可以消费
                Thread.sleep(1000); // 模拟生产过程中的延迟
            }
        }
    }

    // 消费者线程
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait(); // 队列为空时，消费者等待
                }
                int value = queue.poll(); // 消费数据
                System.out.println("Consumed: " + value);
                notify(); // 通知生产者可以生产
                Thread.sleep(1000); // 模拟消费过程中的延迟
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        // 启动生产者线程
        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 启动消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
