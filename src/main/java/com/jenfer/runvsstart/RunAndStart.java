package com.jenfer.runvsstart;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunAndStart {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("thread is running");
        });
        thread.setName("thread");

        thread.run();

    }


}
