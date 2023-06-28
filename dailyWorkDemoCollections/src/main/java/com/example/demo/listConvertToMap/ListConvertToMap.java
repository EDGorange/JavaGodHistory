package com.example.demo.listConvertToMap;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-28 14:56
 **/

public class ListConvertToMap {

    public static void main(String[] args) throws Exception {
        test();
    }
    public static void test() throws Exception {
        List<User> list = Lists.newArrayList(
                User.builder().id(1).age(16).name("小张").build(),
                User.builder().id(10).age(20).name("小孙").build(),
                User.builder().id(1).age(18).name("李四").build(),
                User.builder().id(3).age(6).name("王五").build()
        );

        // 当集合对象key重复时可根据`(oldData, newData) -> newData`设置保留新值还是旧值，这里是保留新值
        Map<Integer, User> map1 = list.stream().collect(Collectors.toMap(User::getId, t -> t, (oldData, newData) -> newData));
        System.out.println(JSONUtil.toJsonStr(map1));

        Map<Integer, String> map2 = list.stream().collect(Collectors.toMap(User::getId, User::getName, (oldData, newData) -> newData));
        System.out.println(JSONUtil.toJsonStr(map2));

        List<User> list2 = map2.entrySet().stream()
                .map(
                        e -> User.builder().id(e.getKey()).name(e.getValue()).build()
                )
                .collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(list2));

        test_listToMap();

        Phone phone = new Phone();
        new Thread(() -> phone.sms(), "A").start();
        new Thread(() -> phone.sms(), "B").start();
        phone.sms();
    }


    public static void test_listToMap() throws Exception {
        List<String> list = Lists.newArrayList("zhengqingya");
        Map<String, String> result = listToMap(list, str -> {
            return "hello:" + str;
        });
        System.out.println(result);
        Map<String, String> result2 = listToMap(list, str -> {
            return "你好:" + str;
        });
        System.out.println(result2);
    }

    public static <T, R> Map<T, R> listToMap(List<T> list, Function<T, R> function) {
        HashMap<T, R> hashMap = Maps.newHashMap();
        for (T t : list) {
            hashMap.put(t, function.apply(t));
        }
        return hashMap;
    }



    static class Phone {
        public synchronized void sms() {
            System.out.println(Thread.currentThread().getName() + ": sms");
            this.call(); // 这里也有锁
        }

        public synchronized void call() {
            System.out.println(Thread.currentThread().getName() + ": call");
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private Integer id;
        private String name;
        private Integer age;
        private Date time;
    }


}
