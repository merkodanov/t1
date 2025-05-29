package com.t1.aop;

import com.t1.infrastucture.TimeLimitExceedLog;
import com.t1.repository.TimeLimitExceedLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MetricAspect {
    @Value("${app.executionTime}")
    private long executionTime;
    private final TimeLimitExceedLogRepository timeLimitExceedLogRepository;

    @Around("@annotation(com.t1.aop.annotation.TrackTime)")
    public Object logMethodPerfomance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("Вызов метода: {}", proceedingJoinPoint.getSignature().toShortString());
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long resultTime = endTime - startTime;
            log.debug("Время выполнения: {} ms", (resultTime));
            if (resultTime > executionTime) {
                String signature = proceedingJoinPoint.getSignature().toShortString();
                timeLimitExceedLogRepository.save(new TimeLimitExceedLog(signature, resultTime));
            }
        }
        return result;
    }
}
