package com.jenfer.sleepvsyield;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class SleepAndYield {

    public static void main(String[] args) {


//        Thread thread = new Thread(() -> {
//            try {
//                for(int i =0;i<10;i++){
//                    Thread.sleep(2000);
//                    log.info("当前时间{}",new Date(System.currentTimeMillis()));
//                }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//        });
//        thread.start();
//
        YieldThread yieldThread1 = new YieldThread();
        yieldThread1.setName("yieldThread1");

        YieldThread yieldThread2 = new YieldThread();
        yieldThread2.setName("yieldThread2");

        yieldThread1.start();
        yieldThread2.start();


    }


}

@Slf4j
class YieldThread extends Thread {
    @Override
    public void run() {

        for (int i = 1; i <= 100; i++) {
            log.info(getName()+":"+i);
            if(i%10==0){
                Thread.yield();
            }
        }
    }
}