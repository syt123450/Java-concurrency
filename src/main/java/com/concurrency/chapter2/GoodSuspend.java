package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */
public class GoodSuspend {

    public static Object object = new Object();

    public static class ChangeThread extends Thread {

        private boolean suspendSignal = false;

        public void suspendThread() {
            suspendSignal = true;
        }

        public void resumeThread() {
            suspendSignal = false;
            synchronized (this) {
                notify();
            }
        }

        public void run() {
            while (true) {

                //不在这里锁住自己有可能会继续执行synchronized(object)部分的代码导致出现问题
                synchronized (this) {
                    if (suspendSignal) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (object) {
                    System.out.println("Begin change.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.yield();
            }
        }
    }

    public static class ReadThread extends Thread {

        public void run() {
            while (true) {
                synchronized (object) {
                    System.out.println("Begin read.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ChangeThread changeThread = new ChangeThread();
        ReadThread readThread = new ReadThread();

        changeThread.start();
        readThread.start();

        Thread.sleep(1000);

        System.out.println("Suspend change thread ======");
        changeThread.suspendThread();

        Thread.sleep(2000);

        System.out.println("Resume change thread ======");
        changeThread.resumeThread();
    }
}
