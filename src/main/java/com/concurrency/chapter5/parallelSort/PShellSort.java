package com.concurrency.chapter5.parallelSort;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ss on 2017/8/10.
 */
public class PShellSort {

    private int[] array;

    public class ShellTask implements Runnable {

        private int i;
        private int h;
        private CountDownLatch countDownLatch;

        public ShellTask(int i, int h, CountDownLatch countDownLatch) {
            this.i = i;
            this.h = h;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            if (array[i] < array[i - h]) {
                int temp = array[i];
                int j = i - h;
                while (j >= 0 && array[j] > temp) {
                    array[j + h] = array[j];
                    j = j - h;
                }
                array[j + h] = temp;
            }
            countDownLatch.countDown();
        }
    }

    public void sort(int[] array) {

        this.array = array;

    }

    public static void main(String[] args) {

        int[] array = new int[]{3, 4, 8, 6, 7, 5, 1};
        PShellSort pShellSort = new PShellSort();
        pShellSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
