package com.t1.aop;

import com.t1.infrastucture.CacheManager;
import com.t1.infrastucture.CacheValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CacheAspect {
    private final CacheManager cacheManager;

    @Around("@annotation(com.t1.aop.annotation.Cacheable)")
    public Object checkCache(ProceedingJoinPoint proceedingJoinPoint) {
        String id = proceedingJoinPoint.getArgs()[0].toString();
        return cacheManager.getOrCompute(id, () -> {
            try {
                log.debug("Запись с id {} добавлена в кеш", id);
                Object result = proceedingJoinPoint.proceed();
                return new CacheValue(result, System.currentTimeMillis());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }
}
