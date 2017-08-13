package com.concurrency.chapter6.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ss on 2017/8/12.
 */
public class CombineCompletableFuture {

    public static Integer cal(int a) {
        return a / 2;
    }

    public static void main(String[] args) throws Exception {

//        CompletableFuture<Integer> intFuture1 = CompletableFuture.supplyAsync(() -> CombineCompletableFuture.cal(50));
//        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> CombineCompletableFuture.cal(25));
//
//        CompletableFuture<Void> future = intFuture1.thenCombine(intFuture2, (i, j) -> (i + j))
//                .thenApply((str) -> "\"" + str + "\"")
//                .thenAccept(System.out::println);
//
//        future.get();

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> CombineCompletableFuture.cal(50))
                .thenCombine(CompletableFuture.supplyAsync(() -> CombineCompletableFuture.cal(25)), (i, j) -> (i + j))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);

        future.get();
    }
}
