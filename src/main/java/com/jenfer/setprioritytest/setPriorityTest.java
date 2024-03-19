package com.jenfer.setprioritytest;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class setPriorityTest {
    public static void main(String[] args) {



        for(int j =0;j<5;j++){

            Thread thread1 = new Thread(()->{
                long startTime = System.currentTimeMillis();
                int count = 0;
                for(int i =0;i<100;i++){
                    new Random().nextInt();
                    count+=i;
                }
                long endTime = System.currentTimeMillis();
                log.info("thread1 is running {} ms",endTime-startTime);
            });


            Thread thread2 = new Thread(()->{

                long startTime = System.currentTimeMillis();
                int count = 0;
                for(int i =0;i<100;i++){
                    new Random().nextInt();
                    count+=i;
                }
                long endTime = System.currentTimeMillis();
                log.info("thread2 is running {} ms",endTime-startTime);
            });
            thread1.start();
            thread1.setPriority(10);
            thread2.start();
            thread2.setPriority(1);

        }


    }

}
