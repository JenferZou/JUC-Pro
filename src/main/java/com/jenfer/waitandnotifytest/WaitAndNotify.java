package com.jenfer.waitandnotifytest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitAndNotify {

    static final Object lock = new Object();
    public static void main(String[] args) {
        Thread t1 =  new Thread(()->{
             synchronized (lock) {
                 try {
                     log.info("线程{}开始等待",Thread.currentThread().getName());
                     lock.wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 log.info("线程{}被唤醒",Thread.currentThread().getName());

             }
         },"t1");

        Thread t2 =  new Thread(()->{
            synchronized (lock) {
                try {
                    log.info("线程{}开始等待",Thread.currentThread().getName());
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程{}被唤醒",Thread.currentThread().getName());

            }
        },"t2");

        t1.start();
        t2.start();


        try {
            // 模拟主线程执行一段耗时操作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock){
            lock.notifyAll();
        }
    }


}
