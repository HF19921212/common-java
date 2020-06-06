package com.city.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectjTest {

    @Pointcut("execution(* *.test(..))")
    public void test(){

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("do beforeTest method");
    }

    @After("test()")
    public void afterTest(){
        System.out.println("do afterTest method");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) throws Throwable {
        Object o = null;
        System.out.println("do aroundTest before method");
        o =  p.proceed();
        System.out.println("do aroundTest after method");
        return o;
    }

}
