package com.concurrency.chapter3.threadpool;

/**
 * Created by ss on 2017/8/5.
 */
public class TestExceptionInfo {

    private Exception clientTrace() {
        return new Exception("Client stack trace");
    }

    public static void main(String[] args) {
        TestExceptionInfo test = new TestExceptionInfo();
        test.clientTrace().printStackTrace();
    }
}
