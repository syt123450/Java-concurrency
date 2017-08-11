package com.concurrency.chapter3.control;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ss on 2017/7/20.
 */

//CyclicBarrier和CountDownLatch不同之处就是可以重复利用，一次完成之后可以进行第二次计数
public class CyclicBarrierDemo {

    private static class Soldier implements Runnable {

        private CyclicBarrier cyclicBarrier;
        private int id;

        public Soldier(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {

            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(id * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(id + ":finish work.");
        }
    }

    private static class BarrierRun implements Runnable {

        private boolean flag;

        public BarrierRun(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (!flag) {
                System.out.println("集合完毕.");
                flag = true;
            } else {
                System.out.println("任务完成.");
            }
        }
    }

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new BarrierRun(false));
        System.out.println("集合");
        for (int i = 0; i < 10; i++) {
            System.out.println("士兵" + i + "报道");
            new Thread(new Soldier(i, cyclicBarrier)).start();
        }
    }
}
