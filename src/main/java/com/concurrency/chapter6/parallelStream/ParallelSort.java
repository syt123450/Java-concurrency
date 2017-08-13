package com.concurrency.chapter6.parallelStream;

import java.util.Arrays;

/**
 * Created by ss on 2017/8/12.
 */
public class ParallelSort {

    public static void main(String[] args) {

        int[] array = new int[]{3, 4, 8, 6, 7, 5, 1};
        Arrays.parallelSort(array);
        System.out.println(Arrays.toString(array));
    }
}
