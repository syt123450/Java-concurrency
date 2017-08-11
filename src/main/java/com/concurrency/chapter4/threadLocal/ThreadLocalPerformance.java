package com.concurrency.chapter4.threadLocal;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ss on 2017/8/5.
 */
public class ThreadLocalPerformance {

    private static final int GEN_COUNT = 10000000;
    private static final int THREAD_COUNT = 4;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Random random = new Random(123);

    //从结果可以看出ThreadLocal可以极大减少竞争
    private static ThreadLocal<Random> tRandom = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RandomTask implements Callable<Long> {

        private int mode;

        public RandomTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom() {
            if (mode == 0) {
                return random;
            } else {
                //get可以将初始值设置到localThread对象中，默认初始值是null，但是可以修改初始值，如果不要null，那么可以通过判断null来set
                return tRandom.get();
            }
        }

        @Override
        public Long call() {

            long b = System.currentTimeMillis();
            for (long i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " spend " + (e - b) + " ms.");

            return e - b;
        }
    }

    public static void main(String[] args) throws Exception {
        Future<Long>[] futures = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = executorService.submit(new RandomTask(0));
        }
        long totalTime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += futures[i].get();
        }
        System.out.println("多线程同时访问一个random： " + totalTime);

        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = executorService.submit(new RandomTask(1));
        }
        totalTime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += futures[i].get();
        }
        System.out.println("使用ThreadLocal包装：" + totalTime);
    }
}
