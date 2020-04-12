package ru.aplaksin.part4.demo1;

import java.util.*;
import java.util.stream.IntStream;

public class Worker {
    private Random random = new Random();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        IntStream.range(0,1000).forEach(index -> {
            stageOne();
            stageTwo();
        });
    }

    public void main() {
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(this::process);
        Thread t2 = new Thread(this::process);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time take: "+ (end - start));
        System.out.println("list1: " + list1.size() + "; list2: " + list2.size());
    }
}
