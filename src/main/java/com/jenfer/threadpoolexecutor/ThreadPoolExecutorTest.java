package com.jenfer.threadpoolexecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest
{
    public static void main(String[] args) {
        // 创建一个线程池，包括5个核心线程，最多同时运行10个线程，空闲线程的存活时间为60秒，使用无界队列
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 提交10个任务给线程池执行
        for (int i = 0; i < 10; i++) {
            executor.submit(new Task(i));
        }

        // 关闭线程池
        executor.shutdown();
    }

    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // 模拟任务执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task " + taskId + " is completed.");
        }
    }
}
