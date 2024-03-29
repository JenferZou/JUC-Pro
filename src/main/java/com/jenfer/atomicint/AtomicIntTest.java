package com.jenfer.atomicint;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntTest {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
            log.info("Increment thread finished.");
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrementAndGet();
            }
            log.info("Decrement thread finished.");
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Final counter value: " + counter.get());
    }
}
