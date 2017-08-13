package com.concurrency.chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ss on 2017/8/12.
 */
public class AskThread implements Runnable {

    CompletableFuture<Integer> result = null;

    public AskThread(CompletableFuture<Integer> result) {
        this.result = result;
    }

    @Override
    public void run() {
        int re = 0;
        try {
            re = result.get() * result.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(re);
    }

    public static void main(String[] args) throws Exception {

        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();

        Thread.sleep(3000);

        future.complete(60);
    }
}
