package com.concurrency.chapter3.control;

import java.util.concurrent.Semaphore;

/**
 * Created by ss on 2017/7/20.
 */
public class SemaphoreDemo implements Runnable {

    private static final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(new SemaphoreDemo()).start();
        }
    }
}
