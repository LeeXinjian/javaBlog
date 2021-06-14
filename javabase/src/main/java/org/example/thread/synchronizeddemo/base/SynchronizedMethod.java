package org.example.thread.synchronizeddemo.base;

public class SynchronizedMethod {

    /**
     * @param args
     *
     * javap -c -l -s -v SynchronizedMethod.class
     */
    public static void main(String[] args) {

    }

    /**
     * 方法上加锁
     */
    public synchronized void sychMethod() {
        System.out.println("SomeThing");
    }

    /**
     * 对象锁
     */
    public void sychInner() {
        synchronized (this) {
            System.out.println("Other Things");
        }
    }
}
