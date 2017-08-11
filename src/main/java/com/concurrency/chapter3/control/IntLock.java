package com.concurrency.chapter3.control;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/20.
 */
public class IntLock implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int startLock;

    public IntLock(int startLock) {
        this.startLock = startLock;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + ":start");
        try {
            if (startLock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getId() + ":interrupted");
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":退出");
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new IntLock(1));
        Thread thread2 = new Thread(new IntLock(2));

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}


        thread1.interrupt();

    }

}
