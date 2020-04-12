package ru.aplaksin;

public class Chapter0 {
    public static void main(String[] args) throws InterruptedException {
        Thread th = Thread.currentThread();
        System.out.println("Current thread: " + th.getName());
        th.setName("My thread");
        System.out.println("Current thread: " + th.getName());
        Thread.sleep(10000);
    }
}
