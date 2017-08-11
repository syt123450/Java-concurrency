package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */


//i++不能保证原子性，会被多线程写坏,就算加了volatile也不行
public class MultiAddi {

    private static volatile Integer i = 0;

    public static class Plus implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                i++;

                //为什么在这里加锁不行？
//                synchronized (i) {
//                    i++;
//                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Plus());
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println(i);
    }
}
