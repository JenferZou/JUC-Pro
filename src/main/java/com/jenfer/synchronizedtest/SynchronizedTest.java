package com.jenfer.synchronizedtest;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class SynchronizedTest {

    public static void main(String[] args) {

        TestSyn testSyn = new TestSyn();
        Thread thread1 =  new Thread(()->{
            synchronized (testSyn){
                log.info(ClassLayout.parseInstance(testSyn).toPrintable());
            }
            synchronized (SynchronizedTest.class){
                SynchronizedTest.class.notify();
            }
        },"thread1");
        thread1.start();

        Thread thread2 =  new Thread(()->{
            synchronized (testSyn){
                log.info(ClassLayout.parseInstance(testSyn).toPrintable());
            }
            log.info(ClassLayout.parseInstance(testSyn).toPrintable());

        },"thread2");
        thread2.start();



    }



}

class TestSyn{
}