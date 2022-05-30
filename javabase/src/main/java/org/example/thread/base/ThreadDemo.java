package org.example.thread.base;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(() -> System.out.println("abc"));

        FutureTask<String> futureTask = new FutureTask<>(() -> "abc");
        new Thread(futureTask).start();
        futureTask.get();

        CompletableFuture.runAsync(()-> System.out.println("abc"));
        CompletableFuture
                .supplyAsync(()->"abc")
                .thenApply(String::length)
                .whenComplete((v,e)-> System.out.println(v));
    }
}
