package com.wfz.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class PushService {
    @Autowired
    private ThreadService1 threadService;

    public void oldPush(){
        int dataNum=10000;
        int[] array=new int[dataNum];
        for (int i=0;i<dataNum;i++){
            array[i]=i;
        }
        long start=System.currentTimeMillis();

        for (int i=0;i<array.length;i++){
            pushSend(array[i]);
        }
        long end=System.currentTimeMillis();
        System.out.println((end-start)+"ms");
    }

    public void pushNew(){
        int dataNum=10000;
        int[] array=new int[dataNum];
        for (int i=0;i<dataNum;i++){
            array[i]=i;
        }
        long start=System.currentTimeMillis();

        for (int i=0;i<10;i++){
            int s=i*1000;
            int e=i*1000+1000-1;
            threadService.push(array,s,e);
        }

        long end=System.currentTimeMillis();
        System.out.println((end-start)+"ms");
    }

    public void pushNewCall(){
        int dataNum = 10000;
        int[] array = new int[dataNum];
        for (int i = 0;i<dataNum;i++){
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        List<Future> futureList= new ArrayList<>();
        for (int i = 0 ; i < 10;i++){
            int s = i * 1000;
            int e = i * 1000 + 1000 - 1;

            ThreadPoolTaskExecutor asyncServiceExecutor = new ThreadPoolTaskExecutor();
            asyncServiceExecutor.initialize();
            Future<Integer> submit=asyncServiceExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return threadService.push1(array,s,e);
                }
            });
            futureList.add(submit);
        }

        for (Future future:futureList) {
            try {
                System.out.println("本轮线程执行数量："+future.get());
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }
        }
        long end=System.currentTimeMillis();
        System.out.println((end-start)+"ms");

    }
    public void pushSend(int data){
        try {
            TimeUnit.MILLISECONDS.sleep(1);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }





}
