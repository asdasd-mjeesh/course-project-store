package com.asdasd.mjeesh.store.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class ServiceLayerMethodsCallLoggerAdvice {

    @Around("com.asdasd.mjeesh.store.aop.Pointcuts.allServiceLayerMethods()")
    public Object loggingServiceProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var arguments = joinPoint.getArgs();

        log.info("\n\n" +
                 "---START METHOD:\t" + methodSignature.getName() +
                 "\n   ARGUMENTS:\t" + Arrays.toString(arguments) +
                 "\n   RETURN TYPE:\t" + methodSignature.getReturnType());

        Object result = joinPoint.proceed();

        log.info("\n   METHOD RESULT:\t" + result +
                 "\n---END METHOD:\t\t\t" + methodSignature.getName() + "\n\n");

        return result;
    }
}
