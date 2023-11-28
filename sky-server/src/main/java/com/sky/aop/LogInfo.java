package com.sky.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogInfo {

    @Pointcut("execution(* com.sky.controller..*.*(..))")
    private void log(){}

    @Before("log()")
    public void logInfo(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("Aop截取的参数为:{}", args);
    }
}
