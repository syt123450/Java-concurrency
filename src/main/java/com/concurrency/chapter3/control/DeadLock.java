package com.concurrency.chapter3.control;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/20.
 */
public class DeadLock implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int startLock;

    public DeadLock(int startLock) {
        this.startLock = startLock;
    }

    @Override
    public void run() {
        if (startLock == 1) {
            lock1.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            lock2.lock();
        } else {
            lock2.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            lock1.lock();
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new DeadLock(1));
        Thread thread2 = new Thread(new DeadLock(2));

        thread1.start();
        thread2.start();
    }
}
