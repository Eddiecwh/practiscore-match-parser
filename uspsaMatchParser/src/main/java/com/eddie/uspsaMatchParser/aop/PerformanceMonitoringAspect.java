package com.eddie.uspsaMatchParser.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoringAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Around("execution(* com.eddie.uspsaMatchParser.service..*(..))")
    public Object runTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        long duration = end - start;
        LOGGER.info("Runtime: "  + duration + "ms");
        return result;
    }
}
