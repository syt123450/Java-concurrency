package com.concurrency.chapter4.noLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ss on 2017/8/6.
 */
public class AtomicIntegerDemo {

    private static AtomicInteger integer = new AtomicInteger();

    private static class AddInteger implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                integer.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new AddInteger());
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println(integer);
    }
}
