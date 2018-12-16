package com.theshy.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2220:09
 * com.theshy.utilsMyDailyProject
 */
public class Logger {
    public void pringLog(){
        System.out.println("Logger日誌已經輸出！");
    }

    /**
     * 前置通知
     */
    public void beforePrintLog() {
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
    }

    /**
     * 后置通知
     */
    public void afterReturningPrintLog() {
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了。。。");
    }

    /**
     * 异常通知
     */
    public void afterThrowingPrintLog() {
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
    }

    /**
     * 最终通知
     */
    public void afterPrintLog() {
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
    }

    public Object aroundPringLog(ProceedingJoinPoint joinPoint){
        Object returnValue = null;
        Object[] args = joinPoint.getArgs();

        try {
        System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");
            returnValue = joinPoint.proceed(args);
        System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");
        } catch (Throwable throwable) {
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");

            throwable.printStackTrace();
        }finally {
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。最终");
        }
        return returnValue;

    }
}
