package com.sky.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogInfo {

    @Pointcut("execution(* com.sky.controller.admin.*.*(..))")
    private void log(){}

    @Before("log()")
    public void logInfo(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();

        String name = signature.getName();
        log.info("\033[1;32m" + "{}类下的{}方法：Aop截取的参数为:{}", signature.getDeclaringType(), name, Arrays.toString(args));
    }
}
