package com.concurrency.chapter2;

/**
 * Created by ss on 2017/7/15.
 */

//跑了好几次发现这里还是有很多次low比high先结束
public class TestPriority {

    private static Object object = new Object();

    private static class HighPriorityThread implements Runnable {

        private int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("High priority finish count.");
                        break;
                    }

                }
            }
        }
    }

    private static class LowPriorityThread implements Runnable {

        private int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("Low priority finish count.");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread highPriorityThread = new Thread(new HighPriorityThread());
        Thread lowPriorityThread = new Thread(new LowPriorityThread());

        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);

        lowPriorityThread.start();
        highPriorityThread.start();
    }
}
