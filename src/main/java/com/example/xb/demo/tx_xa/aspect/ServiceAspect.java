package com.example.xb.demo.tx_xa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    @Pointcut("@annotation(com.example.xb.demo.tx_xa.aspect.LogAnnotation)")
    public void LogAnnotation() {

    }

    @Around(value = "LogAnnotation() && @annotation(logAnnotation)")
    public Object doAround(ProceedingJoinPoint pjp, LogAnnotation logAnnotation) throws Throwable {
        System.out.println("LogAnnotation, " + logAnnotation.value() + "start");
        try {
            return pjp.proceed();
        } finally {
            System.out.println("LogAnnotation, " + logAnnotation.value() + "end");
        }
    }
}
