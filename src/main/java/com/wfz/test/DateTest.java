package com.wfz.test;

import org.joda.time.DateTime;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);

        DateTime dateTime1 = new DateTime(2022, 2, 8, 15, 17, 0);
        System.out.println(dateTime1);

        DateTime dateTime2 = new DateTime(1618119910486L);
        System.out.println(dateTime2);

        DateTime dateTime3 = new DateTime(new Date());
        System.out.println(dateTime3);

        DateTime dateTime4 = new DateTime("2021-04-11T13:45:10.594+08:00");
        System.out.println(dateTime4);
    }
}
