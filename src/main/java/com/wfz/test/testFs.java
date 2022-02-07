package com.wfz.test;


import org.apache.ibatis.logging.stdout.StdOutImpl;

import javax.sound.midi.Soundbank;

public class testFs {
    public static void main(String[] args) throws ClassNotFoundException {

        Class c1 = Class.forName("com.wfz.test.PushService");
        System.out.println(c1);

        Class<PushService> c2 = PushService.class;
        System.out.println(c2);

        PushService pushService = new PushService();
        Class c3 = pushService.getClass();
        System.out.println(c3);
    }
}
