package org.example.thread.queue.demo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {

        final SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
        final LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(10);

        Thread putThread = new Thread(
                () -> {
                    System.out.println("put thread start");
                    try {
                        queue.put(1);
                        linkedBlockingQueue.put(1);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("put thread end");
                });

        Thread takeThread = new Thread(
                () -> {
                    System.out.println("take thread start");
                    try {
                        Integer peek = linkedBlockingQueue.take();
                        System.out.println(peek);

                        System.out.println("take from putThread: " + queue.take());
                    } catch (InterruptedException e) {
                    }
                    System.out.println("take thread end");
                });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();
    }
}
