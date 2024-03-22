package com.example.demo.test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-03-21 17:14
 **/

public class TestMethods {
    public static void main(String[] args) {
        System.out.println(getMapperClassName("driver_risk_control_new"));
    }


    public static Class getMapperClassName(String tableName) {
        //1: tableCode格式都为下划线格式，转为对应的驼峰名称+Mapper即为mapperClass名称。
        if (tableName == null || tableName.isEmpty()) {
            throw new IllegalArgumentException("Table name cannot be null or empty");
        }

        // 转换为小写
        String lowercaseTableName = tableName.toLowerCase();

        // 使用正则表达式将下划线分隔的单词转换为驼峰命名
        String camelCaseName = Arrays.stream(lowercaseTableName.split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(""));

        // 添加Mapper后缀
        String mapperClassName =  camelCaseName + "Mapper";
        try {
            return Class.forName(mapperClassName);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("获取mapperClass出错：" + e);
        }
    }
}
