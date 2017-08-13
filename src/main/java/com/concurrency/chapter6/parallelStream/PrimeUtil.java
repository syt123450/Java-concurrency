package com.concurrency.chapter6.parallelStream;

import java.util.stream.IntStream;

/**
 * Created by ss on 2017/8/12.
 */
public class PrimeUtil {

    public static boolean isPrime(int number) {

        int tmp = number;
        if (tmp < 2) {
            return false;
        }

        for (int i = 2; Math.sqrt(tmp) >= i; i++) {
            if (tmp % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        long t1 = System.currentTimeMillis();
        System.out.println(IntStream.range(1, 1000000).filter(PrimeUtil::isPrime).count());
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        //串行时间是270，并行时间是80，使用的时间明显减少
        long t3 = System.currentTimeMillis();
        System.out.println(IntStream.range(1, 1000000).parallel().filter(PrimeUtil::isPrime).count());
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3);
    }
}
