package fanxing;

import java.util.Objects;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-07-11 14:01
 **/

public class FanxingDemo {


    public static void main(String[] args) {
        getFanXing("a");
        getFanXing(1);
        getFanXing3(1, 2, 3, "a", "b");
        ArrayList<String> list = new ArrayList<String>("haha");
        System.out.println(list.getMethods2("123"));
        System.out.println(list.getMethods1());
        Fanxing<String> impl = new Fanxing<String>() {
            @Override
            public void add(String s) {
                System.out.println(s);
            }

            @Override
            public String getE() {
                return null;
            }
        };
        impl.add("456");
        System.out.println(Objects.isNull(impl.getE()));
    }

    //
    public static <T> String getFanXing(T t) {
        System.out.println("aa" + t);
        return "hh" + t;
    }
    public static <T> String getFanXing3(T... args) {
        for (T arg : args) {
            System.out.println(arg);
        }
        return "hh" + args[0];
    }

    static class ArrayList<T> {
        T t;

        public ArrayList(T t) {
            this.t = t;
        }

        T getMethods1() {
            return t;
        };
         Boolean getMethods2(T t) {
           return t instanceof Integer;
        };

    };

    interface Fanxing<E> {
      void add(E e);
      E getE();
    };

}

