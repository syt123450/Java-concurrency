package com.concurrency.chapter6.parallelStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/8/12.
 */
public class CollectionParallel {

    public static class User {
        private int id;

        public User(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return "User:" + id;
        }
    }

    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            userList.add(new User(i));
        }

        double average = userList.stream().mapToInt(user -> user.id).average().getAsDouble();
        System.out.println(average);

        //get parallel stream from collection
        double average1 = userList.parallelStream().mapToInt(user -> user.id).average().getAsDouble();
        System.out.println(average1);
    }
}
