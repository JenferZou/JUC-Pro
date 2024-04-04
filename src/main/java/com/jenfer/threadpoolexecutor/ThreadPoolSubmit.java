package com.jenfer.threadpoolexecutor;

import java.util.concurrent.*;

public class ThreadPoolSubmit {
    public static void main(String[] args) {
        // 创建一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                      // 核心线程数
                5,                      // 最大线程数
                10,                     // 线程空闲时间
                TimeUnit.SECONDS,       // 时间单位
                new LinkedBlockingQueue<>()   // 任务队列
        );

        // 提交任务并获取 Future 对象
        Future<String> futureResult = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 模拟耗时操作
                Thread.sleep(3000);
                return "Task completed";
            }
        });

        // 执行其他操作...

        try {
            // 获取任务的执行结果，如果任务还没有完成，则阻塞直到任务完成
            String result = futureResult.get();
            System.out.println("任务结果：" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }
}
