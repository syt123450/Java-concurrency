package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class BadSuspend {

    public static Object object = new Object();

    public static class SuspendThread implements Runnable {

        private String name;

        public SuspendThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(name + " get object.");
                Thread.currentThread().suspend();
                System.out.println(name + " release object.");
            }
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new SuspendThread("t1"));
        Thread t2 = new Thread(new SuspendThread("t2"));

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t1.resume();
        t2.resume();
    }
}
