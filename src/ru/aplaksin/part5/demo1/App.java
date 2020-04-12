package ru.aplaksin.part5.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Processor implements Runnable {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + id);
    }
}

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0,5).forEach(index -> {
            executor.submit(new Processor(index));
        });
        executor.shutdown();
        System.out.println("All tasks submitted.");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed.");
    }
}
