package org.example.thread.aqs.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static final ReentrantLock reentrantLock = new ReentrantLock(false);


    public static void main(String[] args) {

        Condition notifyPrint2 = reentrantLock.newCondition();
        Condition notifyPrint1 = reentrantLock.newCondition();


        new Thread(() -> {
            while (true) {
                try {
                    reentrantLock.lock();
                    System.out.println("1");
                    notifyPrint1.signal();
                    notifyPrint2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                try {

                    reentrantLock.lock();
                    System.out.println("2");
                    notifyPrint2.signal();
                    notifyPrint1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }).start();

    }

    public void useReentrantLockDemo() {
        // 创建锁实例
        ReentrantLock lock = new ReentrantLock();
        // 做锁操作
        lock.lock();
        try {
            // 业务代码
            doSomeThing();
        } finally {
            // 解锁操作
            lock.unlock();
        }
    }

    private void doSomeThing() {
        System.out.println("我啥也没干");
    }
}

