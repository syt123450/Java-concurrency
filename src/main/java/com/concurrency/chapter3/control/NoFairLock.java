package com.concurrency.chapter3.control;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/20.
 */
public class NoFairLock implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            System.out.println(Thread.currentThread().getId() + ":get lock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new NoFairLock());
        Thread thread2 = new Thread(new NoFairLock());

        thread1.start();
        thread2.start();
    }
}
