package com.eddie.uspsaMatchParser.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.eddie.uspsaMatchParser.service.MatchService.getCompetitorById(..)) && args(competitorId)")
    public Object validateAndUpdateCompetitor(ProceedingJoinPoint pjp, int competitorId) throws Throwable {
        if (competitorId < 0) {
            LOGGER.info("CompetitorId was negative: " + competitorId);
            competitorId = -competitorId;
        }

        Object result = pjp.proceed(new Object[]{competitorId});
        LOGGER.info("VALIDATION!: " + competitorId);

        return result;
    }
}
