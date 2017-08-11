package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class WaitAndNotify {

    public final static Object object = new Object();

    public static class waiter implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("waiter get object,");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("stop waiting.");
            }
        }
    }

    public static class notifier implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("notifier get object.");
                object.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread waiterThread = new Thread(new waiter());
        Thread notifierThread = new Thread(new notifier());

        waiterThread.start();
        notifierThread.start();
    }
}
