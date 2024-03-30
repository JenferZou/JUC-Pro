package com.jenfer.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArraryTest {
    // 定义一个长度为5的原子整型数组
    private static final AtomicIntegerArray atomicArray = new AtomicIntegerArray(5);

    public static void main(String[] args) {
        // 初始化数组的值
        for (int i = 0; i < atomicArray.length(); i++) {
            atomicArray.set(i, i);
        }

        // 输出初始值
        System.out.println("Initial array values:");
        printArray(atomicArray);

        // 启动多个线程对数组进行原子操作
        for (int i = 0; i < atomicArray.length(); i++) {
            int finalI = i;
            new Thread(() -> incrementArray(finalI)).start();
        }

        // 等待所有线程执行完毕
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出更新后的数组值
        System.out.println("Updated array values:");
        printArray(atomicArray);
    }

    // 对数组的指定索引进行原子加1操作
    private static void incrementArray(int index) {
        atomicArray.getAndIncrement(index);
    }

    // 输出数组的值
    private static void printArray(AtomicIntegerArray array) {
        for (int i = 0; i < array.length(); i++) {
            System.out.println("Index " + i + ": " + array.get(i));
        }
    }
}
