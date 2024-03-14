package com.jenfer.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateRunnableThread {

    public static void main(String[] args) {
//        //创建任务对象
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                log.info("thread name:{}",Thread.currentThread().getName());
//            }
//        };
        //创建任务对象
        Runnable runnable = () -> {
            log.info("thread name:{}", Thread.currentThread().getName());
        };

        //创建线程对象
        Thread thread = new Thread(runnable,"subThread");
        //启动线程
        thread.start();

        log.info("thread name:{}",Thread.currentThread().getName());

    }
}
