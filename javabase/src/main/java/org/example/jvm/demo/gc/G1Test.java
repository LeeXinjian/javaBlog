package org.example.jvm.demo.gc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * G1 相关博客:
 * <p>
 * 官方文档：
 * 认识G1: https://tech.meituan.com/2016/09/23/g1.html
 * 基础知识：
 * https://blog.csdn.net/xiaoye319/article/details/85252195
 * https://tech.meituan.com/2020/08/06/new-zgc-practice-in-meituan.html
 * 参数手册：http://www.manongjc.com/detail/52-qcjzzlboswkpyxd.html
 * 性能调优案例：
 * https://mp.weixin.qq.com/s/xrfaGBkaX8P_7AP8M_Ytcg
 * http://openinx.github.io/ppt/hbaseconasia2017_paper_18.pdf
 * <p>
 * <p>
 *
 -XX:+UseG1GC
 -XX:MaxGCPauseMillis=200
 -XX:+PrintGC
 -XX:+PrintGCDetails
 -Xloggc:gc.log
 -XX:+PrintGCTimeStamps
 -XX:+PrintGCDateStamps
 -XX:+PrintHeapAtGC
 -XX:+PrintReferenceGC
 */
public class G1Test {

    static ExecutorService theadpool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        while (true) {
            int nextValue = random.nextInt();
            for (int i = 0; i < nextValue; i++) {
                theadpool.submit(
                        () -> {
                            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                            byte[] commit = new byte[threadLocalRandom.nextInt() * 1024];
                        }
                );

                int randomSleepTime = random.nextInt() * 10;
                TimeUnit.MILLISECONDS.sleep(randomSleepTime);

            }

            int randomSleepTime = random.nextInt() * 100;
            TimeUnit.MILLISECONDS.sleep(randomSleepTime);
        }

    }
}
//-XX:+UseG1GC
//        -XX:MaxGCPauseMillis=200
//        -XX:+PrintGC
//        -XX:+PrintGCDetails
//        -XX:+PrintGCTimeStamps
//        -XX:+PrintGCDateStamps
//        -XX:+PrintHeapAtGC
//        -XX:+PrintReferenceGC
//        -Xmx10240m
//        -Xms10240m
//        -Xmn2560m