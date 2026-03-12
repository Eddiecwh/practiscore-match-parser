package com.eddie.uspsaMatchParser.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, classname.method name(arguments)
    @Before("execution(* com.eddie.uspsaMatchParser.service.*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method called (BEFORE) " + jp.getSignature());
    }

    @After("execution(* com.eddie.uspsaMatchParser.service.*.*(..))")
    public void logMethodExecuted(JoinPoint jp) {
        LOGGER.info("Method has finished (AFTER) " + jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.eddie.uspsaMatchParser.service.*.*(..))")
    public void logMethodExecutedFailed(JoinPoint jp) {
        LOGGER.info("Method has some issues (THROWING) " + jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.eddie.uspsaMatchParser.service.*.*(..))")
    public void logMethodExecutedSuccess(JoinPoint jp) {
        LOGGER.info("Method has been cleared! (AFTER RETURNING) " + jp.getSignature().getName());
    }
}
