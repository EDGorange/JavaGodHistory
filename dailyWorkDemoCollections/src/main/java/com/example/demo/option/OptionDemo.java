package com.example.demo.option;

import java.util.Optional;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-28 14:17
 **/

public class OptionDemo {
    public static void main(String[] args) throws Exception {
        //testGet();
        testEmpty();
        testMain(new String[]{"123"});
    }

    public static void testGet() throws Exception {
        System.out.println(Optional.ofNullable(null).get());
    }

    public static void testEmpty() throws Exception {
        String s = null;
        System.out.println(Optional.ofNullable(Optional.empty()).get());
        Optional.ofNullable(s).ifPresent(n -> System.out.println(n));
    }


    public static void testMain(String[] args) {
        String name = "John";
        Optional<String> optionalName = Optional.ofNullable(name);

        // 使用ifPresent方法处理存在的情况
        optionalName.ifPresent(n -> System.out.println("Name: " + n));

        // 使用orElse方法处理不存在的情况
        String defaultName = optionalName.orElse("Unknown");
        System.out.println("Default Name: " + defaultName);

        // 使用orElseGet方法处理不存在的情况，并提供自定义逻辑
        String customName = optionalName.orElseGet(() -> {
            // 自定义逻辑，比如从数据库或其他地方获取默认值
            return "Custom Name";
        });
        System.out.println("Custom Name: " + customName);

        // 使用map方法对存在的值进行转换
        Optional<String> upperCaseName = optionalName.map(n -> n.toUpperCase());
        upperCaseName.ifPresent(n -> System.out.println("Upper Case Name: " + n));
    }
}
