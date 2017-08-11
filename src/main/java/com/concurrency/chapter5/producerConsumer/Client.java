package com.concurrency.chapter5.producerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ss on 2017/8/7.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Data> queue = new LinkedBlockingQueue<>();

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(producer3);

//        executorService.execute(consumer1);
//        executorService.execute(consumer2);
//        executorService.execute(consumer3);

        Thread.sleep(1000);

        producer1.stop();
        producer2.stop();
        producer3.stop();

//        consumer1.stop();
//        consumer2.stop();
//        consumer3.stop();

        System.out.println(queue);

        System.out.println("All producer stopped.");
        Thread.sleep(2000);

        executorService.shutdown();
    }
}
