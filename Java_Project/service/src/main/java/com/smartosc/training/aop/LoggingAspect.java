package com.smartosc.training.aop;

import com.smartosc.training.entities.ApiLog;
import com.smartosc.training.services.impl.ApiLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 10/07/2020 - 3:50 PM
 * @created_by Hieupv
 * @since 10/07/2020
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Autowired
    private ApiLogServiceImpl apiLogService;

    @Pointcut("within(com.smartosc.training..*)")
    public void service() {
    }

//    @Around("service()")
//    public Object aroundServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object result = proceedingJoinPoint.proceed();
//        return result;
//    }

    @Around("execution(* com.smartosc.training.services.*.*(..))")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        ApiLog apiLog = new ApiLog();
        apiLog.setCalledTime(Calendar.getInstance().getTime());
        apiLog.setErrorMessage("hahah");
        apiLog.setRetryNum(1);
        List<String> args = new ArrayList<>();
        String[] argNames = codeSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        for (int i = 0; i < argNames.length; i++) {
            args.add(argNames[i] + ":" + argValues[i].toString());
        }
        apiLog.setData(String.join(", ", args));
        apiLogService.saveApiLog(apiLog);
        log.error(String.join(", ", args));
        return result;
    }
}
