package com.concurrency.chapter3.control;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/20.
 */
public class TimeLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getId() + ":Finish work.");
            } else {
                System.out.println(Thread.currentThread().getId() + ":Failed to get lock.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new TimeLock());
        Thread thread2 = new Thread(new TimeLock());

        thread1.start();
        thread2.start();
    }
}
