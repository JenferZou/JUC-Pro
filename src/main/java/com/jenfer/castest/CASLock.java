package com.jenfer.castest;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Random;


public class CASLock {
    private final AtomicReference<Thread> owner = new AtomicReference<>();
    private final int maxRetries = 100;

    public void lock() {
        Thread currentThread = Thread.currentThread();
        int retries = 0;

        while (!owner.compareAndSet(null, currentThread)) {
            // 如果锁已经被其他线程占用，自旋等待
            if (++retries > maxRetries) {
                // 如果尝试次数超过最大重试次数，则放弃获取锁
                throw new RuntimeException("Failed to acquire the lock");
            }
            Thread.yield(); // 让出CPU，避免自旋过度消耗CPU资源
            sleepRandom(); // 增加随机性
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        if (!owner.compareAndSet(currentThread, null)) {
            throw new IllegalMonitorStateException("Calling thread does not hold the lock");
        }
    }

    private void sleepRandom() {
        try {
            // 随机休眠一小段时间，增加竞争机会
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        CASLock lock = new CASLock();

        // 创建并启动10个线程，每个线程执行1000次加锁和解锁操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    lock.lock();
                    // 执行临界区代码
                    System.out.println(Thread.currentThread().getName() + " acquired the lock");
                    lock.unlock();
                }
            }).start();
        }
    }
}
