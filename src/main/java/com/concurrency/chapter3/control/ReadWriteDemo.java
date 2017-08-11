package com.concurrency.chapter3.control;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * Created by ss on 2017/7/20.
 */

//可以在main中输入不同的锁进行对比
public class ReadWriteDemo {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static WriteLock writeLock = reentrantReadWriteLock.writeLock();
    private static ReadLock readLock = reentrantReadWriteLock.readLock();

    private static int value = 0;

    private static class ReadThread implements Runnable {

        Lock readThreadLock;

        public ReadThread(Lock lock) {
            this.readThreadLock = lock;
        }

        @Override
        public void run() {
            readThreadLock.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println(value);
            readThreadLock.unlock();
        }
    }

    private static class WriteThread implements Runnable {

        //不同的锁都是继承自Lock只要在初始化具体的锁后，之后就可以直接使用lock接口了，当然前提是只需要使用lock和unlock等基本方法
        Lock writeThreadLock;

        public WriteThread(Lock lock) {
            this.writeThreadLock = lock;
        }

        @Override
        public void run() {
            writeThreadLock.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            value = 2;
            writeThreadLock.unlock();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 18; i++) {
//            new Thread(new ReadThread(readLock)).start();
            new Thread(new ReadThread(reentrantLock)).start();
        }

        for (int i = 0; i < 2; i++) {
//            new Thread(new WriteThread(writeLock)).start();
            new Thread(new WriteThread(reentrantLock)).start();
        }
    }
}
