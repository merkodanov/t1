package com.t1.aop;

import com.t1.infrastucture.DataSourceErrorLog;
import com.t1.repository.DataSourceErrorLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLogAspect {
    private final DataSourceErrorLogRepository dataSourceErrorLogRepository;

    @Around("@annotation(LogDataSourceError)")
    public Object dataSourceError(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            log.debug("Аспект сработал на {} Класса: {}", joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringType());
            DataSourceErrorLog dataSourceErrorLog = DataSourceErrorLog.builder()
                    .message(ex.getMessage())
                    .signatureOfMethod(String.valueOf(joinPoint.getSignature()))
                    .stackTrace(Arrays.toString(ex.getStackTrace()))
                    .build();
            dataSourceErrorLogRepository.save(dataSourceErrorLog);
            // Пробрасываю исключение наверх (в контроллер)
            throw ex;
        }
    }
}
