package com.concurrency.chapter5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ss on 2017/8/10.
 */
public class Div implements Runnable {

    public static BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Message message = blockingQueue.take();
                System.out.println(message.orgStr + " = " + message.j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
