package com.concurrency.chapter4.noLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by ss on 2017/8/6.
 */
public class AtomicReferenceDemo {

    private static AtomicReference<Integer> integerAtomicReference = new AtomicReference<>();

    private static class AddThread implements Runnable {

        @Override
        public void run() {

            int operation = 0;

            while (operation < 1000) {
                Integer integer = integerAtomicReference.get();
                if (integerAtomicReference.compareAndSet(integer, integer + 1)) {
                    operation++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        integerAtomicReference.set(0);
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

        System.out.println(integerAtomicReference.get());
    }
}
