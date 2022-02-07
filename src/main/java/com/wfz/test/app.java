package com.wfz.test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

public class app {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        BookService jdkProxy=(BookService) new JDKProxy().createBookServiceProxy(bookService.getClass());
        jdkProxy.createBook();

        Integer I;

        I= new Integer(2);
        System.out.println(I);
    }
}


