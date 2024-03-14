package com.jenfer.threadtest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateThread {

    public static void main(String[] args) {
        //创建一个线程
        Thread thread = new Thread(()->{
            log.info("thread name:{}",Thread.currentThread().getName());
        });
        //设置新创建线程名字
        thread.setName("subThread");
        //启动新创建的线程
        thread.start();

        //主线程打印
        log.info("thread name:{}",Thread.currentThread().getName());

    }
}
