package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class NoVisibility {

    //不加volatile就读不到ready的改变，加了才可以读的到
//    private static volatile boolean ready;
    private static boolean ready;
    private static int number;

    private static class ReadThread implements Runnable {

        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread readThread = new Thread(new ReadThread());
        readThread.start();

        Thread.sleep(1000);

        number = 50;
        ready = true;

        Thread.sleep(1000);
    }
}
