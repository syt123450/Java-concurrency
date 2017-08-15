package com.concurrency.chapter6.atomicEnhance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by ss on 2017/8/14.
 */
public class PerformanceTest {

    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT = 10000000;

    private static AtomicLong atomicLong = new AtomicLong(0);
    private static LongAdder longAdder = new LongAdder();
    private static long count = 0;

    private static CountDownLatch cdlSync = new CountDownLatch(TASK_COUNT);
    private static CountDownLatch cdlAtomic = new CountDownLatch(TASK_COUNT);
    private static CountDownLatch cdlAdder = new CountDownLatch(TASK_COUNT);

    private static synchronized long increase() {
        return ++count;
    }

    private static synchronized long getCount() {
        return count;
    }

    private static class SyncThread implements Runnable {

        private long startTime;

        public SyncThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long count = getCount();
            while (count < TARGET_COUNT) {
                count = increase();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endTime - startTime) + "ms, count = " + count);
            cdlSync.countDown();
        }
    }

    private static void testSync() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_THREADS; i++) {
            executorService.submit(new SyncThread(startTime));
        }
        cdlSync.await();
        executorService.shutdown();
    }

    private static class AtomicThread implements Runnable {

        private long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {

            long count = atomicLong.get();
            while (count < TARGET_COUNT) {
                count = atomicLong.incrementAndGet();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("AtomicThread spend: " + (endTime - startTime) + "ms, count = " + count);
            cdlAtomic.countDown();
        }
    }

    private static void testAtomic() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_THREADS; i++) {
            executorService.submit(new AtomicThread(startTime));
        }
        cdlAtomic.await();
        executorService.shutdown();
    }

    private static class LongAdderThread implements Runnable {

        private long startTime;

        public LongAdderThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {

            long count = longAdder.sum();
            while (count < TARGET_COUNT) {
                longAdder.increment();
                count = longAdder.sum();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("LongAdder spend: " + (endTime - startTime) + "ms, count = " + count);
            cdlAdder.countDown();
        }
    }

    private static void testLongAdder() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            executorService.submit(new LongAdderThread(startTime));
        }
        cdlAdder.await();
        executorService.shutdown();
    }

    public static void main(String[] args) throws Exception {
        testSync();
        testAtomic();
        testLongAdder();
    }
}
