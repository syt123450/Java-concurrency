package com.concurrency.chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ss on 2017/8/12.
 */
public class ComposeCompletableFuture {

    public static Integer cal(Integer a) {
        return a / 2;
    }

    public static void main(String[] args) throws Exception {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> ComposeCompletableFuture.cal(50))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> ComposeCompletableFuture.cal(i)))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);

        future.get();
    }
}
