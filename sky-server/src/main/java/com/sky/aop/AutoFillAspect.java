package com.sky.aop;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    private void autoFillPointCut() {
    }

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段的自动填充");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();

        Long currentId = BaseContext.getCurrentId();
        if (operationType == OperationType.INSERT) {
            try {

                Method setCreateTime = args[0].getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setCreateUser = args[0].getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateTime = args[0].getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = args[0].getClass().getDeclaredMethod("setUpdateUser", Long.class);

                setCreateTime.invoke(args[0], now);
                setUpdateTime.invoke(args[0], now);
                setCreateUser.invoke(args[0], currentId);
                setUpdateUser.invoke(args[0], currentId);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operationType == OperationType.UPDATE) {
            try {
                Method setUpdateTime = args[0].getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = args[0].getClass().getDeclaredMethod("setUpdateUser", Long.class);
                setUpdateTime.invoke(args[0], now);
                setUpdateUser.invoke(args[0], currentId);
            }catch (Exception e){
                e.printStackTrace();
                log.error("自动填充失败");
            }
        }
    }
}
