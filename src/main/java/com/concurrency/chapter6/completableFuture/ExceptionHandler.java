package com.concurrency.chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ss on 2017/8/12.
 */
public class ExceptionHandler {

    public static Integer cal(int a) {
        return a / 0;
    }

    public static void main(String[] args) throws Exception {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> ExceptionHandler.cal(50))
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return 0;
                })
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);

        future.get();
    }
}
