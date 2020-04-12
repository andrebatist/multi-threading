package ru.aplaksin.part14.demo1;

import java.util.Random;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting.");
        Thread t = new Thread(() -> {
            Random ram = new Random();
            int bound = (int) 1E8;
            for (int index = 0; index < bound; index++) {
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    System.out.println("We've been interrupted");
//                    break;
//                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted!");
                    break;
                }
                Math.sin(ram.nextDouble());
            }
        });
        t.start();
        Thread.sleep(500);
        t.interrupt();
        t.join();

        System.out.println("Finished");
    }
}
