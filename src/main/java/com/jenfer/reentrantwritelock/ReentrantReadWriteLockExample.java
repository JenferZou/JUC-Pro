package com.jenfer.reentrantwritelock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private static int sharedResource = 0;

    public static void main(String[] args) {
        // 启动多个读取线程
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                readData();
            }).start();
        }

        // 启动一个写入线程
        new Thread(() -> {
            writeData(100);
        }).start();
    }

    public static void readData() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is reading data: " + sharedResource);
            // 模拟读取操作
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void writeData(int value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is writing data...");
            // 模拟写入操作
            sharedResource = value;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}