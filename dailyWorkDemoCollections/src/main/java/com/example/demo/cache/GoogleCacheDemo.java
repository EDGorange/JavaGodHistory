package com.example.demo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-28 16:32
 **/

public class GoogleCacheDemo {
    public static void main(String[] args) {
        Cache<Object, Object> build = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.MINUTES).build(new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object key) throws Exception {
                CacheLoaderCallable cacheLoaderCallable = new CacheLoaderCallable();
                return cacheLoaderCallable.call();
            }
        });
    }
    static class CacheLoaderCallable implements Callable<Table<String, String, String>> {

        @Override
        public Table<String, String, String> call() throws Exception {

            Table<String, String, String> cacheTable = HashBasedTable.create();
            Map<String, String> stringStringHashMap = new HashMap<>();
            String primaryKey = "testing";
            stringStringHashMap.put("1", "1");
            stringStringHashMap.put("2", "2");
            stringStringHashMap.put("3", "3");
            stringStringHashMap.entrySet().stream().forEach(
                entry -> cacheTable.put(primaryKey, entry.getKey(), entry.getValue())
            );
            return cacheTable;
        }
    }
}
