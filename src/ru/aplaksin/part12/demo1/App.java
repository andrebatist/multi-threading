package ru.aplaksin.part12.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        IntStream.range(0,200).forEach(index -> executor.submit(() -> Connection.getInstance().connect()));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
