package com.concurrency.chapter5.parallelSort;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ss on 2017/8/10.
 */
public class POddEvenSort {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int[] array;
    private int exchangeFlag = 1;

    private synchronized void setExchangeFlag(int flag) {
        exchangeFlag = flag;
    }

    private synchronized int getExchangeFlag() {
        return exchangeFlag;
    }

    private class SortTask implements Runnable {

        private CountDownLatch countDownLatch;
        private int i;

        public SortTask(int i, CountDownLatch countDownLatch) {
            this.i = i;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                setExchangeFlag(1);
            }
            countDownLatch.countDown();
        }
    }

    public void sort(int[] array) throws InterruptedException {
        this.array = array;
        int start = 0;
        while (start == 1 || getExchangeFlag() == 1) {
            setExchangeFlag(0);
            CountDownLatch countDownLatch = new CountDownLatch(array.length / 2 -  (array.length % 2 == 0 ? start : 0));

            for (int i = start; i < array.length - 1; i += 2) {
                executorService.submit(new SortTask(i, countDownLatch));
            }

            countDownLatch.await();

            start = 1 - start;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int[] array = new int[]{3, 4, 8, 6, 7, 5, 1};
        POddEvenSort pOddEvenSort = new POddEvenSort();
        pOddEvenSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
