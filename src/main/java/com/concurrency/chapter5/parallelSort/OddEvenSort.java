package com.concurrency.chapter5.parallelSort;

import java.util.Arrays;

/**
 * Created by ss on 2017/8/10.
 */

//serial OddEven Sort

public class OddEvenSort {

    public void sort(int[] array) {
        int exchangeFlag = 1, start = 0;

        while (exchangeFlag == 1 || start == 1) {
            exchangeFlag = 0;
            for (int i = start; i < array.length - 1; i += 2) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    exchangeFlag = 1;
                }
            }
            start = 1 - start;
        }
    }

    public static void main(String[] args) {

        int[] array = new int[]{3, 4, 8, 6, 7, 5, 1};
        OddEvenSort oddEvenSort = new OddEvenSort();
        oddEvenSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
