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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
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
    @Value("${application.repository.query-limit-warning-ms}")
    private int executionLimitMs;

    private int retryCount = 0;

    @Autowired
    private ApiLogServiceImpl apiLogService;

    @Pointcut("within(com.smartosc.training..*)")
    public void service() {
    }

    @Autowired
    private RetryTemplate retryTemplate;

    @Around("execution(* com.smartosc.training.repositories.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        String message = joinPoint.getSignature() + " exec in " + executionTime + " ms";
        if (executionTime >= executionLimitMs) {
            log.warn(message + " : SLOW QUERY");
        }
        return proceed;
    }

    @Around("execution(* com.smartosc.training.services.*.*(..))")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        retryTemplate.execute(retryCallback -> {
                    Object result;
                    CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
                    ApiLog apiLog = new ApiLog();
                    apiLog.setCalledTime(Calendar.getInstance().getTime());

                    apiLog.setRetryNum(++retryCount);
                    log.info("Attempting at {} time(s)", retryCount);
                    List<String> args = new ArrayList<>();
                    String[] argNames = codeSignature.getParameterNames();
                    Object[] argValues = joinPoint.getArgs();
                    for (int i = 0; i < argNames.length; i++) {
                        args.add(argNames[i] + ":" + argValues[i].toString());
                    }
                    apiLog.setData(String.join(", ", args));
                    try {
                        result = joinPoint.proceed();
                    } catch (ResourceAccessException e) {
                        log.error("time out exception", e);
                        apiLog.setErrorMessage(e.getMessage());
                        throw e;
                    } catch (Exception e) {
                        log.error("Server exception", e);
                        apiLog.setErrorMessage(e.getMessage());
                        throw e;
                    } finally {
                        apiLogService.saveApiLog(apiLog);
                    }
                    log.error(String.join(", ", args));
                    return result;
                }
//        },
//        recoveryCallback -> {
//            log.info("Recovering");
//            return null;
//        }
        );
        return null;
    }
}
