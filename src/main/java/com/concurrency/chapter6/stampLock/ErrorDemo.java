package com.concurrency.chapter6.stampLock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by ss on 2017/8/14.
 */
public class ErrorDemo {

    private static Thread[] holdCPUThread = new Thread[3];
    private static final StampedLock lock = new StampedLock();

    private static class HoldCPUThread implements Runnable {

        @Override
        public void run() {
            long readLock = lock.readLock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
            lock.unlockRead(readLock);
        }
    }

    public static void main(String[] args) throws Exception {

        new Thread() {
            @Override
            public void run() {
                long readLock = lock.readLock();
                LockSupport.parkNanos(600000000000L);
                lock.unlock(readLock);
            }
        }.start();

        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {
            holdCPUThread[i] = new Thread(new HoldCPUThread());
            holdCPUThread[i].start();
        }

        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {
            holdCPUThread[i].isInterrupted();
        }
    }
}
