package com.concurrency.chapter5.parallelSort;

import java.util.Arrays;

/**
 * Created by ss on 2017/8/10.
 */
public class BubbleSort {

    public static void sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 8, 6, 7, 5, 1};
        BubbleSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
