package ru.aplaksin.part14.demo2;

import java.util.Random;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting.");
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> fu = exec.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                Random ran = new Random();
                int bound = (int) 1E8;
                for (int index = 0; index < bound; index++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    Math.sin(ran.nextDouble());
                }
                return null;
            }
        });

        exec.shutdown();
        Thread.sleep(500);
        exec.shutdownNow();
        //fu.cancel(true);
        exec.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished");
    }
}
