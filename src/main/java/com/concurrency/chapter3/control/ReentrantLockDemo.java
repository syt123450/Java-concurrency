package com.concurrency.chapter3.control;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/16.
 */
public class ReentrantLockDemo implements Runnable {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {

            //必须要自己加锁与释放
            reentrantLock.lock();
            i++;
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new ReentrantLockDemo());
        Thread thread2 = new Thread(new ReentrantLockDemo());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}
