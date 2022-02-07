package com.wfz.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ThreadService1 {
    @Async("asyncServiceExecutor")
    public void push(int[] array,int start,int end){
        long s=System.currentTimeMillis();
        for(int i=start;i<=end;i++){
            pushSend(array[i]);
        }
        long e=System.currentTimeMillis();
        System.out.println((e-s)+"ms");
    }

    public void pushSend(int dataNum) {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int push1(int[] array,int start,int end){
        int count=0;
        long s=System.currentTimeMillis();
        for (int i=start;i<=end;i++){
            count++;
            pushSend(array[i]);
        }
        long e=System.currentTimeMillis();
        System.out.println((e-s)+"ms");
        return count;
    }
}
