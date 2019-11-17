package com.gnaf.thread.concurrent;

import com.gnaf.proxy.demo01.Run;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private int strides = 0;
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        Horse.barrier = barrier;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}

public class Run2_CyclicBarrier_HorseRace {

    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public Run2_CyclicBarrier_HorseRace(int nHorses, final int pause) {
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    s.append("=");
                }
                System.out.println(s);
                for (Horse hors : horses) {
                    System.out.println(hors.tracks());
                }
                for (Horse hors : horses) {
                    if (hors.getStrides() >= FINISH_LINE) {
                        System.out.println(hors + "won!");
                        exec.shutdownNow();
                        return;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("barrier=action sleep interrupted");
                }
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {

        int nHorses = 7;
        int pause = 200;
        new Run2_CyclicBarrier_HorseRace(nHorses, pause);

    }
}
