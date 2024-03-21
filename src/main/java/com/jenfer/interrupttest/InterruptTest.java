package com.jenfer.interrupttest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptTest {

    public static void main(String[] args) {


        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                log.error("线程是否处于中断状态{}", Thread.currentThread().isInterrupted());
                e.printStackTrace();
                log.info("还在运行");
            }
            log.info("线程运行结束");

        }, "Thread1");

        thread.start();
        thread.interrupt();

        log.info("======================================================");

        Thread thread2 = new Thread(() -> {
            log.info("线程是否处于中断状态{}", Thread.currentThread().isInterrupted());
        }, "Thread2");
        thread2.start();
        thread2.interrupt();

    }


}
