package com.concurrency.chapter6.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * Created by ss on 2017/8/11.
 */
public class PrintDemo {

    public static void main(String[] args) {

//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
////        numbers.forEach((Integer value) -> System.out.println(value));
//        numbers.forEach(System.out::println);

        int[] array = {1, 2, 3, 4, 5, 6};

//        for (int i: array) {
//            System.out.println(i);
//        }

//        Arrays.stream(array).forEach(new IntConsumer() {
//            @Override
//            public void accept(int value) {
//                System.out.println(2 * value);
//            }
//        });

//        Arrays.stream(array).forEach((final int x) -> {
//            System.out.println(2 * x);
//        });

//        Arrays.stream(array).forEach(System.out::println);

        Arrays.stream(array).forEach((x) -> System.out.println(x));
    }
}
