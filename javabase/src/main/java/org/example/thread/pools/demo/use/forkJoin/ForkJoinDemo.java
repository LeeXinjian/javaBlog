package org.example.thread.pools.demo.use.forkJoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

public class ForkJoinDemo {

    public static Long count(List<Long> data) {
        final long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(data);
        Long result = pool.invoke(task);
//        ForkJoinTask<Long> submit = pool.submit(task);
//        Long aLong = submit.get();
        System.out.println("Executed with fork/join in (ms): " + (System.currentTimeMillis() - start));
        return result;
    }

    public static void main(String[] args) {
        List<Long> data = LongStream
                .range(1, 10000000)
                .boxed()
                .collect(toList());
        System.out.println(ForkJoinDemo.count(data));
    }

    //子任务需要继承RecursiveTask
    static class SumTask extends RecursiveTask<Long> {
        private List<Long> data;

        public SumTask(List<Long> data) {
            this.data = data;
        }

        @Override
        protected Long compute() {// 递归方法
            // 当需要计算的长度50000时，直接计算结果
            if (data.size() <= 50000) {
                return computeSumDirectly();
            } else {
                int mid = data.size();
                // 拆分任务
                SumTask firstSubtask = new SumTask(data.subList(0, mid));
                SumTask secondSubtask = new SumTask(data.subList(mid, data.size()));
                // 提交子任务
                firstSubtask.fork();
                secondSubtask.fork();
                // 等待子任务执行，合并结果
                return firstSubtask.join() + secondSubtask.join();
            }
        }

        private long computeSumDirectly() {
            long sum = 0;
            for (Long l : data) sum += l;
            return sum;
        }
    }
}
