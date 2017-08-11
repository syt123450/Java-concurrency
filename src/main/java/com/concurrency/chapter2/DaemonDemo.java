package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */

//当程序内只有守护进程时会结束所有进程并自动退出
public class DaemonDemo {

    private static class ADaemon implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("print in daemon.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread daemon = new Thread(new ADaemon());

        daemon.setDaemon(true);
        daemon.start();

        Thread.sleep(2000);
    }
}
