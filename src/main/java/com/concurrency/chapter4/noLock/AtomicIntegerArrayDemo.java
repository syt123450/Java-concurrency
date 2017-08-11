package com.concurrency.chapter4.noLock;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by ss on 2017/8/6.
 */
public class AtomicIntegerArrayDemo {

    private static AtomicIntegerArray array = new AtomicIntegerArray(10);

    private static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                array.incrementAndGet(i % array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new AddThread());
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println(array);
    }
}
