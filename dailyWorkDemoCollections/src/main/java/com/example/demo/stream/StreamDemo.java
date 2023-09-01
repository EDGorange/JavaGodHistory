package com.example.demo.stream;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-09-01 14:42
 **/

public class StreamDemo {
    public static void main(String[] args) {


    }


    public void testStream() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();
        //使用Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println);


        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
        //过滤器// 匹配第一个
        Optional<Integer> findFirst = list.stream().parallel().filter(x -> x>6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        List<String> fiterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                .collect(Collectors.toList());

        //聚合
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));

        // 自然排序
        Optional<Integer> max1 = list.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        Optional<Person> max3 = personList.stream().max(Comparator.comparingInt(Person::getSalary));


        long count = list.stream().filter(x -> x > 6).count();
        //映射：
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());


        //规约：
        // 求和方式1
        Optional<Integer> sum4 = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum5 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum6 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);


        // 求最大值方式1
        Optional<Integer> max7 = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max8 = list.stream().reduce(1, Integer::max);

        //统计：
        // 求总数
        Long count9 = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        Optional<Integer> max9 = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        //分区和分组;
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        //接合（joining）：

        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list9 = Arrays.asList("A", "B", "C");
        String string = list9.stream().collect(Collectors.joining("-"));

        //排序：

        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
    }
    @Data
    class Person {
        private String name; // 姓名
        private int salary; // 薪资
        private int age; // 年龄
        private String sex; //性别
        private String area; // 地区


        // 构造方法
        public Person(String name, int salary, int age,String sex,String area) {
            this.name = name;
            this.salary = salary;
            this.age = age;
            this.sex = sex;
            this.area = area;
        }
        // 省略了get和set，请自行添加


    }



}
