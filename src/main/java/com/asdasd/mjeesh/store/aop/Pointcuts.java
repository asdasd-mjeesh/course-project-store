package com.asdasd.mjeesh.store.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.asdasd.mjeesh.store.service..*(..))")
    public void allServiceLayerMethods() {
    }

}
