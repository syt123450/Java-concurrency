package com.concurrency.chapter3.control;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/7/21.
 */
public class PreparationThreadTest {

    @Test
    public void test() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}