package org.example.algorithm.thread;

import lombok.SneakyThrows;
import org.example.algorithm.util.link.ListNode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MulThread {

    private static final ReentrantLock lock = new ReentrantLock(false);
    private static final Condition condition = lock.newCondition();
    private static volatile boolean isCurrentNeedPrintA = true;

    @SneakyThrows
    public static void main(String[] args) {

        new Thread(
                () -> doPrint(false, "我负责打印-B"), "T2"
        ).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(
                () -> doPrint(true, "我负责打印-A"), "T1"
        ).start();

    }

    /**
     * @param printA       不是不是轮到自己打印
     * @param printContent 打印的内容
     */
    private static void doPrint(boolean printA, String printContent) {
        lock.lock();

        ListNode lessThan,lessThanTmp = new ListNode();
        ListNode equals,equalsTmp = new ListNode();
        ListNode moreThan,moreThanTmp = new ListNode();

        try {
            while (true) {
                // 当前不需要打印
                if (!isNeedPrint(printA)) {
                    // 阻塞当前任务,并放弃锁资源
                    condition.await();
                }
                // 打印
                System.out.println(Thread.currentThread().getName() + "打印了:" + printContent);
                // 取反
                isCurrentNeedPrintA = !isCurrentNeedPrintA;
                // 唤醒等待
                condition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static boolean isNeedPrint(boolean printA) {
        // 如果当前任务需要打印A 但是当前轮到打印A
        // 或者当前任务不需要打印A 当前也没轮到打印A
        return (printA && isCurrentNeedPrintA)
                ||
                (!printA && !isCurrentNeedPrintA);
    }
}
