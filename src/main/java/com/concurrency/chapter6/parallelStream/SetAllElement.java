package com.concurrency.chapter6.parallelStream;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by ss on 2017/8/12.
 */
public class SetAllElement {

    public static void main(String[] args) {

        int[] array = new int[10];
        Random random = new Random();
        Arrays.setAll(array, (i) -> random.nextInt(10));
        System.out.println(Arrays.toString(array));

        Arrays.parallelSetAll(array, (i) -> random.nextInt(100));
        System.out.println(Arrays.toString(array));
    }
}
