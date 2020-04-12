package ru.aplaksin.part10.demo1;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Runner {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        IntStream.range(0,10000).forEach(index -> count++);
    }

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting ....");
        cond.await();
        System.out.println("Woken up.");
        
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press the return key.");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        cond.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
