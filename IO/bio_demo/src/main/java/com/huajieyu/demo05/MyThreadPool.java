package com.huajieyu.demo05;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    private ExecutorService service;

    MyThreadPool(int maximumPoolSize, int queueSize){
        this.service = new ThreadPoolExecutor(3, maximumPoolSize, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void exec(Runnable runnable){
        service.execute(runnable);
    }


}
