package com.concurrency.chapter5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ss on 2017/8/10.
 */
public class Multiply implements Runnable {

    public static BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();

    @Override
    public void run() {

        while (true) {
            try {
                Message message = blockingQueue.take();
                message.j = message.i * message.j;
                Div.blockingQueue.add(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
