package com.jenfer.threadpoolexecutor;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolShutdownAndNow {
    public static void main(String[] args) {
        // 创建一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                      // 核心线程数
                5,                      // 最大线程数
                10,                     // 线程空闲时间
                TimeUnit.SECONDS,       // 时间单位
                new LinkedBlockingQueue<>()   // 任务队列
        );

        // 提交一些任务
        for (int i = 0; i < 5; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务执行：" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // 关闭线程池
        // executor.shutdown(); // 使用 shutdown() 方法

        // 使用 shutdownNow() 方法
        List<Runnable> remainingTasks = executor.shutdownNow();
        System.out.println("剩余任务数：" + remainingTasks.size());

        System.out.println("尝试提交新任务");
        // 提交一个新的任务，看看会怎么样
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("新任务执行：" + Thread.currentThread().getName());
            }
        });

        System.out.println("主线程结束");
    }
}
