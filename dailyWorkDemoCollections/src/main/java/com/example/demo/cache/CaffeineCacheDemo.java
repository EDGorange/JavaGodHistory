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
            System.out.println("睡五秒了");
        }


/*        //缓存示例1：
        Cache<Object, Object> cache = Caffeine.newBuilder().maximumSize(100).expireAfterWrite(1, TimeUnit.MINUTES).build();
        cache.put("keyDemo", "value");
        System.out.println("value demo " + cache.getIfPresent("keyDemo"));*/
/*        //缓存示例2：
        // 创建一个自动加载数据的缓存
             Cache<String, String> cache2 = Caffeine.newBuilder()
                   .maximumSize(100)
                   .expireAfterWrite(1, TimeUnit.MINUTES)
                   .build(key -> {
                        // 当缓存中不存在该键对应的元素时，自动执行这个加载逻辑
                        return "Loaded value for " + key;
                    });
             try {
                 // 尝试从缓存中获取数据，如果不存在则自动加载
                 String value = cache2.get("key1", k -> "Loaded value for " + k);
                 System.out.println("Value: " + value);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }*/
        //缓存示例3：
      /*// 创建一个异步缓存
        AsyncCache<String, String> asyncCache = Caffeine.newBuilder()
              .maximumSize(100)
              .expireAfterWrite(1, TimeUnit.MINUTES)
              .buildAsync();
        // 异步地向缓存中放入数据
       CompletableFuture<Void> putFuture = asyncCache.put("key1", CompletableFuture.completedFuture("value1"));
        try {
            // 等待放入操作完成
            putFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 异步地从缓存中获取数据
        CompletableFuture<String> getFuture = asyncCache.get("key1", k -> CompletableFuture.supplyAsync(() -> "Loaded value for " + k));
        try {
            // 等待获取操作完成并打印结果
            String value = getFuture.get();
            System.out.println("Value: " + value);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

    }
}

