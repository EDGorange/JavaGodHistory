package com.example.demo.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-07-11 10:56
 **/

public class CaffeineCacheDemo {
    public static void main(String[] args) throws InterruptedException {
        AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存；仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据key查询数据库里面的值
                .buildAsync(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });

//异步缓存返回的是CompletableFuture
        CompletableFuture<String> future = asyncLoadingCache.get("1");
        future.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("main thread running ");
        while (true) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("      ");
        }
    }
}
