package org.example.algorithm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MulThreadAbc {

    static ReentrantLock lock = new ReentrantLock();
    static Condition A = lock.newCondition();
    static Condition B = lock.newCondition();
    static Condition C = lock.newCondition();

    static int flag = 0;

    public static void main(String[] args) throws InterruptedException {


        new Thread(
                () -> {
                    while (true) {
                        doPrint(A, B, 0, "A");
                    }
                }, "A").start();


        new Thread(
                () -> {
                    while (true) {
                        doPrint(B, C, 1, "B");
                    }
                }, "B").start();


        new Thread(
                () -> {
                    while (true) {
                        doPrint(C, A, 2, "C");
                    }
                }, "C").start();
    }

    private static void doPrint(Condition cur, Condition next, int curFlag, String a1) {

        lock.lock();
        try {
            // 不该我自己处理
            if (flag != curFlag) {
                cur.await();
            }

            System.out.println(Thread.currentThread().getName() + "-==- " + a1);
            flag++;

            next.signal();
        } catch (InterruptedException e) {
            lock.unlock();
        }
    }
}
