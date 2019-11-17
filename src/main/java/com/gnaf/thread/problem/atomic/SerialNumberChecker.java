package com.gnaf.thread.problem.atomic;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CircularSet {
    private int[] array = new int[1000];
    private int len = 1000;
    private int index = 0;

    public CircularSet() {
        Arrays.fill(array, -1);
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++) {
            if (array[i] == val) {
                return true;
            }
        }
        return false;
    }
}

public class SerialNumberChecker {

    private static CircularSet serials = new CircularSet();
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            exec.execute(new SerialChecker());
        }
    }

}
