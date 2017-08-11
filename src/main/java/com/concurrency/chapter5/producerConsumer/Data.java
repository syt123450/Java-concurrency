package com.concurrency.chapter5.producerConsumer;

/**
 * Created by ss on 2017/8/7.
 */
public final class Data {

    private final int intData;

    public Data(int data) {
        this.intData = data;
    }

    public int getIntData() {
        return this.intData;
    }

    @Override
    public String toString() {
        return "Data-" + intData;
    }
}
