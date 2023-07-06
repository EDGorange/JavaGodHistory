package com.example.demo.threadLocal;

import org.springframework.util.StringUtils;

import java.util.concurrent.ExecutionException;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-29 14:23
 **/

public class ThreadLocalDemo {
    static class House {
        //        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
//            @Override
//            protected Integer initialValue() {
//                return 0;
//            }
//        };
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

        public void saleHouse() {
            this.threadLocal.set(this.threadLocal.get() + 1);
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*House house = new House();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    int randomNum = RandomUtil.randomInt(10);
                    for (int j = 0; j < randomNum; j++) {
                        house.saleHouse();
                    }
                    System.out.println(Thread.currentThread().getName() + " 卖出: " + house.threadLocal.get() + "RandomNum: " + randomNum);
                } catch (Exception e) {
                    e.getStackTrace();
                } finally {
                    // 在阿里巴巴手册中有说明,尽量在try-finally块进行回收
                    house.threadLocal.remove();
                }
            }, "t" + i).start();
        }*/

        /*MyThreadLocal.set("3", Thread.currentThread().getName());
        System.out.println(MyThreadLocal.get("3"));
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {

            System.out.println(executorService
                    .submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println(Thread.currentThread().getName() + "正在执行");
                            MyThreadLocal.set("3", Thread.currentThread().getName());
                            return MyThreadLocal.get("3");
                        }
                    }).get());
        }

        executorService.shutdown();
        Thread.sleep(10000);
        new Thread(() -> {
            System.out.println("----" + MyThreadLocal.get("3"));
        }).start();
        Thread thread = new Thread("myThread11") {
            @Override
            public void run() {
                System.out.println(MyThreadLocal.get("3"));
            }
        };
        thread.start();
        System.out.println("main"+  MyThreadLocal.get("3"));

        System.out.println("--------------------------");
        System.out.println("123" + null);
        String a = null;
        String b = "789A";
        System.out.println("456" + Strings.nullToEmpty(b));
        System.out.println("-===================================");
        System.out.println(Strings.isNullOrEmpty(""));
        System.out.println(Strings.isNullOrEmpty(null));
        System.out.println(Strings.isNullOrEmpty(" "));
        System.out.println("==================================================");
        System.out.println(StringUtils.endsWithIgnoreCase(b, "A"));*/
        String asid = "7000708663FRANCHISEE_MAINT";
//只要字符串
        String str = asid.replaceAll("[0-9]","");
        System.out.println("只要字符串： " + str);
//只要数字
        String num = asid.replaceAll("[a-zA-Z_]","");
        System.out.println("只要数字： " + num);
        System.out.println("%%%%%%%%%%%%%%");
        System.out.println(asid);
        //System.out.println(Arrays.asList(asid.split(num)));
        System.out.println("*********************************");
        System.out.println(asid);
        System.out.println(asid.split(num).length);
        for (int i = 0; i < asid.split("700").length; i++) {
            System.out.println(asid.split("700")[i]);
        }
        System.out.println(asid.split(num)[1]);
        System.out.println(asid.split(num)[0]);


        String test = "123ags";
        for (String s : test.split("123")) {
            System.out.println(s);
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^");
        String test2 = "898LC NEW";
        String orderNo = test2.replaceAll("[a-zA-Z_]", "");
        String[] split = test2.split(StringUtils.trimAllWhitespace(orderNo));
        System.out.println(orderNo);
        System.out.println(test2);
        System.out.println(split[0]);
        System.out.println(split[1]);

        String test3 = "898";
        String orderNo3 = test3.replaceAll("[a-zA-Z_]", "");
        String[] split3 = test3.split(StringUtils.trimAllWhitespace(orderNo3));
        System.out.println(split3.length);
        System.out.println(split3[0]);
        System.out.println(split3[1]);

    }


}
