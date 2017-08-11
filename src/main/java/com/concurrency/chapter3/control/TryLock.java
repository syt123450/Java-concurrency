package com.concurrency.chapter3.control;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ss on 2017/7/20.
 */
public class TryLock implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int startLock;

    public TryLock(int startLock) {
        this.startLock = startLock;
    }

    @Override
    public void run() {
        if (startLock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                    try {
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":Finish job.");

                                //此处return会等后面的finally语句执行完了之后再返回
                                return;
                            } finally {
                                lock2.unlock();
                            }

                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }

        } else {

            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {}
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + ":Finish job.");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new TryLock(1));
        Thread thread2 = new Thread(new TryLock(2));

        thread1.start();
        thread2.start();
    }
}
