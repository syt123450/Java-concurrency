package com.concurrency.chapter5.parallelSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ss on 2017/8/10.
 */
public class PSearch {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private final int THREAD_NUM;
    private final int[] array;
    private AtomicInteger result = new AtomicInteger(-1);

    public PSearch(int number, int[] array) {
        this.THREAD_NUM = number;
        this.array = array;
    }

    private int search(int target, int start, int end) {

        for (int i = start; i < end; i++) {
            if (result.get() != -1) {
                return result.get();
            } else {
                if (array[i] == target) {
                    if (result.compareAndSet(-1, i)) {
                        return result.get();
                    } else {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    private class SearchTask implements Callable<Integer> {

        private int target;
        private int start;
        private int end;

        public SearchTask(int target, int start, int end) {
            this.target = target;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            return search(target, start, end);
        }
    }

    public int pSearch(int target) throws InterruptedException, ExecutionException {

        int subArraySize = array.length / THREAD_NUM + 1;
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < array.length; i += subArraySize) {
            int end = i + subArraySize < array.length ? i + subArraySize : array.length;
            futures.add(executorService.submit(new SearchTask(target, i, end)));
        }

        for (Future<Integer> future : futures) {
            if (future.get() != -1) {
                return future.get();
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{3, 4, 5, 6, 7, 8, 3};
        PSearch pSearch = new PSearch(2, array);
        System.out.println(pSearch.pSearch(8));
    }
}
