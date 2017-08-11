package com.concurrency.chapter5.producerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ss on 2017/8/7.
 */
public class Consumer implements Runnable {

    private boolean isRunning = true;
    private BlockingQueue<Data> queue;

    public Consumer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        System.out.println("Start consumer id = " + Thread.currentThread().getId());

        try {
            while (isRunning || queue.size() != 0) {
                Data data = queue.take();
                if (data != null) {
                    System.out.println("Consumer " + Thread.currentThread().getId() + " get data " + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
