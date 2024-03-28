package com.jenfer.volatiletest;

import java.util.ArrayList;
import java.util.List;

public class VolatileTest {
    private volatile boolean flag = false;
    public static void main(String[] args) {
        VolatileTest example = new VolatileTest();

        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            example.setFlag(true);
            System.out.println("Flag has been set to true.");
        });

        List<Thread> readerThreads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread readerThread = new Thread(() -> {
                while (!example.isFlag()) {
                    // Busy-waiting until flag becomes true
                }
                System.out.println("Flag has been changed to true.");
            });
            readerThreads.add(readerThread);
        }

        writerThread.start();
        for (Thread readerThread : readerThreads) {
            readerThread.start();
        }

        try {
            writerThread.join();
            for (Thread readerThread : readerThreads) {
                readerThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
