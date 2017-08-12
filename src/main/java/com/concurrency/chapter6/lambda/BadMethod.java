package com.concurrency.chapter6.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/8/11.
 */
public class BadMethod {

    public static void main(String[] args) {
        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Double.valueOf(i));
        }
        numbers.stream().map(Double::toHexString).forEach(System.out::println);
    }
}
