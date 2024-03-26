package com.jenfer.jointest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            log.info(Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep(2000); // 模拟执行一些耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " has finished.");
        }, "Thread 1");

        Thread thread2 = new Thread(() -> {
            log.info(Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep(3000); // 模拟执行一些耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " has finished.");
        }, "Thread 2");

        thread1.start();
        thread2.start();

        try {
            // 等待thread1和thread2完成
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("All threads have finished execution.");
    }

}
