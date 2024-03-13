package com.jenfer.threadtest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateThread {

    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            log.info("thread name:{}",Thread.currentThread().getName());
        });

        log.info("thread name:{}",Thread.currentThread().getName());

    }
}
