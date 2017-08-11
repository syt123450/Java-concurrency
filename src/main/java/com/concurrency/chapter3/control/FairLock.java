package com.concurrency.chapter3.control;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/20.
 */
public class FairLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            lock.lock();
            System.out.println(Thread.currentThread().getId() + ":get lock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new FairLock());
        Thread thread2 = new Thread(new FairLock());

        thread1.start();
        thread2.start();
    }
}
