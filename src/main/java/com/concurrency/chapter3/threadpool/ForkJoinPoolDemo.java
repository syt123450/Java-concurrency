package com.concurrency.chapter3.threadpool;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by ss on 2017/8/5.
 */
public class ForkJoinPoolDemo {

    public static class CountTask extends RecursiveTask<Long> {

        private static final int THRESHOLD = 1000;
        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Long compute() {

            System.out.println("In task " + start + "===" + end);

            long sum = 0;

            if (end - start > THRESHOLD) {
                int step = (end - start) / 10;
                ArrayList<CountTask> tasks = new ArrayList<>();
                int startPoint = start;
                for (int i = 0; i < 10; i++) {
                    if (startPoint + step <= end) {
                        CountTask countTask = new CountTask(startPoint, startPoint + step);
                        startPoint += step;
                        tasks.add(countTask);
                        countTask.fork();
                    } else {
                        CountTask countTask = new CountTask(startPoint, end);
                        tasks.add(countTask);
                        countTask.fork();
                    }
                }

                for (CountTask t: tasks) {
                    sum += t.join();
                }

            } else {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            }

            return sum;
        }
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 2000);
        ForkJoinTask<Long> result = forkJoinPool.submit(countTask);

        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
