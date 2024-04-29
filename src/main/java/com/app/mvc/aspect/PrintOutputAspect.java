package com.app.mvc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
@Slf4j
public class PrintOutputAspect {

    @AfterReturning(pointcut = "@annotation(com.app.mvc.aspect.PrintOutput)",returning = "val")
    public void print(JoinPoint point, Object val){
      log.debug("MyLogger :: after returning | "+point.getSignature().toString()+" :: val ==> "+val);
    }
}
