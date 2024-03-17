package com.jenfer.futuretast;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class CreateFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override

            public Integer call() throws Exception {
                log.info("futureTask is running");
                return 1;
            }
        });

        //创建线程,构造器方法上传入任务对象初始化线程
        Thread thread = new Thread(futureTask);
        thread.setName("futureTask");
        thread.start();

        //主线程阻塞，等待futureTask执行完毕得到结果
        Integer result = futureTask.get();
        log.info("result:{}",result);;
    }



}
