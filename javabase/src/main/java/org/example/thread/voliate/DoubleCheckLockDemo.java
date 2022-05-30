package org.example.thread.voliate;

/**
 * 指令重排破坏dcl
 * https://blog.csdn.net/qq_46054356/article/details/123415172
 */
public class DoubleCheckLockDemo {

    /**
     * 避免指令重排
     */
    private static volatile String someThing;

    public static String getString() {
        if (someThing != null) {
            return someThing;
        }

        synchronized (DoubleCheckLockDemo.class) {
            if (someThing == null) {
                someThing = new String("abc");
            }
        }

        return someThing;
    }
}
