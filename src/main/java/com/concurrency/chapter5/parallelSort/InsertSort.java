package com.concurrency.chapter5.parallelSort;

import java.util.Arrays;

/**
 * Created by ss on 2017/8/10.
 */
public class InsertSort {

    public void sort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {

        int[] array = new int[]{3, 4, 8, 6, 7, 5, 1};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
