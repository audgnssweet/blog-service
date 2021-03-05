package com.cos.blog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around(value = "execution(* com.cos.blog..*.*(..))")
    public Object makeLog(ProceedingJoinPoint pjp) throws Throwable {
        logger.info(
            "start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature()
                .getName());
        Object result = pjp.proceed();
        logger.info(
            "finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature()
                .getName());
        return result;
    }
}
