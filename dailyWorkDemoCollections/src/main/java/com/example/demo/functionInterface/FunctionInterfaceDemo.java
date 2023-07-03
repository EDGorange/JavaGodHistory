package com.example.demo.functionInterface;

import org.apache.commons.lang.StringUtils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-30 10:26
 **/

public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        testConsumer();
        testFunction();
        testSupplier();
        testPredicate();
    }

    public static void testSupplier() {
        Supplier<String> supplier = () -> { return String.valueOf(123); };
        System.out.println(supplier.get());
    }

    public static void testFunction() {
        Function<String, Integer> fun = (str) -> {
            return Integer.valueOf(str);
        };
        System.out.println(fun.apply("789"));
    }

    public static void testConsumer() {
        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };
        consumer.accept("123");
    }

    public static void testPredicate() {
        Predicate<String> predicate = StringUtils::isNotEmpty;
        System.out.println(predicate.test(""));
    }
}
