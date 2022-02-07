package com.wfz.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //定义切面
@Component
@Slf4j
public class LogAspectW {
    //定义切点，在执行括号路径的每个方法前执行
    @Pointcut("execution(public void com.wfz.service.ArticleService.listArticle(..))")
    public void pt(){}
    //由于无法预知原始方法运行后是否会抛出异常，因此环绕通知方法必须抛出Throwable对象
    //定义通知，环绕通知原始方法调用可以不接受返回值，通知设置成void；如果接受返回值必须设置object；原始方法返回void则可设void或object
    @Around("pt()")
    public Object doLogging(ProceedingJoinPoint pjp)throws Throwable{
        try {
            log.info("----------------log start--------------------");
            //1. 打印执行的类和方法
            Signature signature = pjp.getSignature();
            String methodName = signature.getName();
            String interfaceName = signature.getDeclaringTypeName();
            log.info("接口名称:{}",interfaceName);
            log.info("方法名称:{}",methodName);
            //2. 打印执行方法的参数
            log.info("方法参数:{}", JSON.toJSONString(pjp.getArgs()));
            //3. 计算方法执行的时间
            long startTime = System.currentTimeMillis();
            //方法调用
            Object ret = pjp.proceed();//重点&必须
            long endTime = System.currentTimeMillis();
            log.info("方法执行时间:{}ms", endTime-startTime);
            log.info("----------------log end--------------------");
            return ret;//重点&必须
        }catch (Exception e){
            //4. 如果有异常，记录异常
            log.error("异常信息",e);
            throw e;
        }
    }//注意启动类里开启AOP支持


}
//前置通知@Before     后置通知@After