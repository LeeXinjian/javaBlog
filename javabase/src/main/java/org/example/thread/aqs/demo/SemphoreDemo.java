package org.example.thread.aqs.demo;

import java.util.concurrent.Semaphore;

public class SemphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        if (semaphore.tryAcquire()) {
            return;
        }
        semaphore.release();
    }
}
