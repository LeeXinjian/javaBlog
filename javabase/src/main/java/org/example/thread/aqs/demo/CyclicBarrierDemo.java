package org.example.thread.aqs.demo;

public class CyclicBarrierDemo {

    /**
     *
     */
    private static volatile Integer a = 0;

    private static Integer b = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(
                () -> {
                    b++;
                    a++;
                }
        ).start();

        new Thread(
                () -> {
                    System.out.println(a);
                    System.out.println(b);
        }).start();


    }
}
