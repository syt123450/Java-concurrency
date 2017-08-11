package com.concurrency.chapter5.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ss on 2017/8/9.
 */
public class TestFutureMode {

    public static void main(String[] args) {

        Client client = new Client();
        Data data = client.request("123");
        System.out.println("get future.");
        System.out.println(data.getResult());
    }
}
