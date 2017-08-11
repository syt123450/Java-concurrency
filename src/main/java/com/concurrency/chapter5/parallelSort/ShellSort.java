package com.concurrency.chapter5.parallelSort;

import java.util.Arrays;

/**
 * Created by ss on 2017/8/10.
 */
public class ShellSort {

    public void sort(int[] array) {

        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }

        while (h > 0) {

            for (int i = h; i < array.length; i++) {
                int key = array[i];
                int j = i - h;
                while (j >= 0 && array[j] > key) {
                    array[j + h] = array[j];
                    j -= h;
                }
                array[j + h] = key;
            }

            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) {

        int[] array = new int[]{3, 4, 8, 6, 7, 5, 1};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
