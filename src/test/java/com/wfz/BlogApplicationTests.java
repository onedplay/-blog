package com.wfz;

import com.wfz.test.PushService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private PushService pushService;
    @Test
    public void testOldPush(){
        pushService.oldPush();
    }

    @Test
    public void testNewPush(){
        pushService.pushNew();
        try {
            TimeUnit.HOURS.sleep(1);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Test
    public void  testNewPushCall(){
        pushService.pushNewCall();
        try {
            TimeUnit.HOURS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void contextloads(){
        redisTemplate.opsForValue().set("name","xiaoming");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
