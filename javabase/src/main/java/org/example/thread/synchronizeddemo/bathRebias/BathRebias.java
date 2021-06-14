package org.example.thread.synchronizeddemo.bathRebias;

import com.google.common.collect.Lists;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;

/**
 * 批量重偏向
 * https://www.cnblogs.com/LemonFive/p/11248248.html
 */
public class BathRebias {

    public static void main(String[] args) throws Exception {

        //创造100个偏向线程t1的偏向锁
        ArrayList<Object> listA = Lists.newArrayList(2);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 2; i++) {
                Object a = new Object();
                synchronized (a) {
                    listA.add(a);
                }
            }
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程t1状态为存活
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        //睡眠3s钟保证线程t1创建对象完成
        Thread.sleep(3000);
        System.out.println("打印t1线程，list中第2个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(1)).toPrintable()));

        //创建线程t2竞争线程t1中已经退出同步块的锁
        Thread t2 = new Thread(() -> {
            Object a = listA.get(1);
            synchronized (a) {
                System.out.println("第2个元素::::");
                System.out.println((ClassLayout.parseInstance(a).toPrintable()));
            }
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        Thread.sleep(3000);

        System.out.println("第三个对象对象头" + (ClassLayout.parseInstance(listA.get(2)).toPrintable()));

    }
}
