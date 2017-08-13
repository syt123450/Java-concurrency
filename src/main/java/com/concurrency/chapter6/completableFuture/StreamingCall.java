package com.concurrency.chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ss on 2017/8/12.
 */
public class StreamingCall {

    public static Integer cal(int a) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return a * a;
    }

    public static void main(String[] args) throws Exception {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> StreamingCall.cal(50))
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        future.get();
    }
}
