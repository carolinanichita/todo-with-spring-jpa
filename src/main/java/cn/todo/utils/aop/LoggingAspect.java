package cn.todo.utils.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("@annotation(cn.todo.utils.aop.LogMethodDetails)")
    public void logMethodCall(JoinPoint jp) throws Throwable {
        logger.info(jp.getSignature() + " is called.");
    }

    @Around("@annotation(cn.todo.utils.aop.LogMethodDetails)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();

        final Object proceed = joinPoint.proceed();

        final long executionTime = System.currentTimeMillis() - start;

        logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms" + " with params " + Arrays.toString(joinPoint.getArgs()));

        return proceed;
    }

    @AfterThrowing(value = "@annotation(cn.todo.utils.aop.LogMethodDetails)", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) throws Throwable {
        logger.error("Exception in executing " + joinPoint.getSignature() + " : " + ex.getMessage());
    }
}
