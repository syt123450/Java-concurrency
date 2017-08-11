package com.concurrency.chapter3.control;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by ss on 2017/7/20.
 */

//park被中断之后不会抛出异常，需要自己通过Thread.interrupted()来判断是否被中断了
public class LockSupportIntDemo {

    private static Object object = new Object();

    private static class InnerClass implements Runnable {

        private int id;

        public InnerClass(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("In " + id);
                LockSupport.park();
                if (Thread.interrupted()) {
                    System.out.println(id + " is interrupted.");
                } else {
                    System.out.println(id + " finished.");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new InnerClass(1));
        Thread thread2 = new Thread(new InnerClass(2));

        thread1.start();
        Thread.sleep(100);
        thread2.start();

        thread1.interrupt();
        LockSupport.unpark(thread2);

        new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }
}
