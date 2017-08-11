package com.concurrency.chapter5.immutable;

/**
 * Created by ss on 2017/8/7.
 */
public final class ImmutableClass {

    private final String name;
    private final int number;
    private final double price;

    public ImmutableClass(String name, int number, double price) {
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }

    public double getPrice() {
        return this.price;
    }
}
