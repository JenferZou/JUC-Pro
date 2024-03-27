package com.jenfer.reentrantlocktest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class ReentrantLockTest {
    private static final ReentrantLock lock = new ReentrantLock();
    private static int count = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            increment();
            increment();
            increment();
        });

        Thread thread2 = new Thread(() -> {
            increment();
            increment();
            increment();
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Final count: " + count);
    }

    public static void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}
