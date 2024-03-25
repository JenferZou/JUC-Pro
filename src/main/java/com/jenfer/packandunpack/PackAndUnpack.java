package com.jenfer.packandunpack;

import java.util.concurrent.*;

public class PackAndUnpack {
    public static void main(String[] args) throws InterruptedException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 4; // 设定线程数量
        int chunkSize = array.length / numThreads; // 计算每个线程处理的数组大小

        // 创建线程数组保存线程实例
        Thread[] threads = new Thread[numThreads];

        // 创建一个数组保存每个线程的执行结果
        int[] results = new int[numThreads];

        // 创建并启动线程
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;
            int[] chunk = new int[endIndex - startIndex];
            System.arraycopy(array, startIndex, chunk, 0, endIndex - startIndex);
            threads[i] = new WorkerThread(chunk, i, results);
            threads[i].start();
        }

        // 等待所有线程执行完成
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        // 合并结果
        int result = 0;
        for (int partialResult : results) {
            result += partialResult;
        }

        System.out.println("Result: " + result);
    }

    static class WorkerThread extends Thread {
        private final int[] array;
        private final int threadIndex;
        private final int[] results;

        public WorkerThread(int[] array, int threadIndex, int[] results) {
            this.array = array;
            this.threadIndex = threadIndex;
            this.results = results;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int num : array) {
                sum += num;
            }
            results[threadIndex] = sum;
        }
    }
}
