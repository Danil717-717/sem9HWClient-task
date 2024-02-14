package ru.springgb.clienttask.logAspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

@Component
@Aspect
public class LoggingAspect {

    @Around("@annotation(ru.springgb.clienttask.logAspect.TrackUserAction)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("./client-executor/src/main/java/ru/springgb/clienttask/logAspect/Log.txt",true))) {
            Date date = new Date();
            long start = System.currentTimeMillis();
            writer.write (date + " - " + joinPoint.getSignature() + " start exec ");
            writer.newLine();
            Object proceed = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            writer.write(date + " - " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
            writer.newLine();
            return proceed;
        }
    }
}
