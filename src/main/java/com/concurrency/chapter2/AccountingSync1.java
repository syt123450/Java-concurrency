package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/16.
 */
public class AccountingSync1 implements Runnable {

    //只需要有任意一个静态object对象来当做锁，让synchronized可以锁住整个类就可以了，不一定要创建一个特定的对象
    private static Object instance = new Object();

    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new AccountingSync1());
        Thread thread2 = new Thread(new AccountingSync1());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);

    }
}
