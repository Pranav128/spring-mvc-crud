package com.app.mvc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
@Slf4j
public class LoggerAspect {

    @Pointcut("execution(* com.app.mvc.controller.*.*(..))")
    public void allControllers(){
    }

    @Pointcut("execution(* com.app.mvc.service.*.*(..))")
    public void allRepositories(){}

    @Before("allControllers()")
    public void log(JoinPoint point){
      log.info("MyLogger :: Executing : "+point.getSignature().toString());
    }


    @AfterThrowing(pointcut = "within(com.app..*)",throwing = "ex")
    public void exceptions(JoinPoint point, Throwable ex){
        log.error("Caught exception in : "+point.getSignature().toString()+" :: "+ex.getMessage());
    }

    @Around("allControllers()")
    public Object processHandle(ProceedingJoinPoint point){
        Object result = null ;
        try{
            result = point.proceed();
        }
        catch (Throwable ex){
            log.error("Caught exception in : "+point.getSignature().toString()+" :: "+ex.getMessage());
        }
        return result;
    }

}
