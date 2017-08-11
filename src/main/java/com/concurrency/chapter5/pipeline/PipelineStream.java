package com.concurrency.chapter5.pipeline;

/**
 * Created by ss on 2017/8/10.
 */
public class PipelineStream {

    public static void main(String[] args) {

        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                Message message = new Message();
                message.i = i;
                message.j = j;
                message.orgStr = "(" + i + " + " + j + ") * " + i + " / 2";
                Plus.blockingQueue.add(message);
            }
        }
    }
}
