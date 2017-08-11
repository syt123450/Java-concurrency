package com.concurrency.chapter3.control;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by ss on 2017/7/20.
 */

//LockSupport中的park和unpark可以替代原来不安全的suspend和resume
public class LockSupportDemo {

    public static Object object = new Object();

    private static class ChangeObjectThread implements Runnable {

        private int id;

        public ChangeObjectThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(id + "start");
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ChangeObjectThread(1));
        Thread thread2 = new Thread(new ChangeObjectThread(2));

        thread1.start();
        Thread.sleep(100);
        thread2.start();

        LockSupport.unpark(thread1);
        LockSupport.unpark(thread2);
    }
}
