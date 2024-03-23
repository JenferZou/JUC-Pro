package com.jenfer.guardedsuspension;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class GuardedSuspension {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(()->{
            List<String> list = null;
            log.info("开始处理数据");
            guardedObject.set(list);
        },"t1").start();

        log.info("开始获取数据");
        Object response = guardedObject.get();
        log.info("获取数据：{}",(List<String>)response);


    }






}
@Slf4j
class GuardedObject{
    private Object response;

    private final Object lock = new Object();

    public Object get(){
        synchronized (lock){
            while (response == null){
                try {
                    log.info("等待结果");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

    }

    public void set(Object response){
        synchronized (lock){
            this.response = response;
            lock.notifyAll();
        }
    }

}