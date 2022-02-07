package com.wfz.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public Object createBookServiceProxy(Class clazz){
        ClassLoader cl = clazz.getClassLoader();
        Class[] classes = clazz.getInterfaces();
        InvocationHandler ih= new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("检查参数");
                Object ret = method.invoke(clazz.getDeclaredConstructor().newInstance(), args);
                System.out.println("事务处理");
                System.out.println("日志记录");
                return ret;
            }
        };
        Object o= Proxy.newProxyInstance(cl,classes,ih);
        return o;
    }
}
