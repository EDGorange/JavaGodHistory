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
    }

    public static void testGet() throws Exception {
        System.out.println(Optional.ofNullable(null).get());
    }

    public static void testEmpty() throws Exception {
        System.out.println(Optional.ofNullable(Optional.empty()).get());
    }

}
