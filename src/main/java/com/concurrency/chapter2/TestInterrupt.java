package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class TestInterrupt {

    public static class innerThread implements Runnable {

        @Override
        public void run() {

            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("break by interrupt");
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted when sleep");
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new innerThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
