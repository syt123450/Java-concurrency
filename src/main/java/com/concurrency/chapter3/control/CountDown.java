package com.concurrency.chapter3.control;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ss on 2017/7/20.
 */
public class CountDown {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    private static class PreparationThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Begin to check.");
                Thread.sleep(1000);
                System.out.println("check complete.");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new PreparationThread()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fire");

    }
}
