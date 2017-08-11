package com.concurrency.chapter5.producerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ss on 2017/8/7.
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<Data> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEP_TIME = 1000;

    public Producer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        Data data = null;
        Random random = new Random();

        System.out.println("Start producer id = " + Thread.currentThread().getId());

        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEP_TIME));
                data = new Data(count.incrementAndGet());

                System.out.println(data + " is put into queue by producer " + Thread.currentThread().getId() + ".");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("Failed to put " + data + " into queue.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
