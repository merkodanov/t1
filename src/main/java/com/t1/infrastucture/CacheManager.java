package com.t1.infrastucture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Component
@Slf4j
public class CacheManager {
    private final Map<String, CacheValue> cache = new ConcurrentHashMap<>();

    @Value("${app.ttl}")
    private int ttl;

    public Object getOrCompute(String key, Supplier<CacheValue> supplier) {
        return cache.computeIfAbsent(key, _ -> supplier.get()).value();
    }

    @Scheduled(fixedDelayString = "${app.ttl}")
    public void cleanUp() {
        cache.entrySet().removeIf(entry -> entry.getValue().creationTime() >= ttl);
    }

    //Метод для контроллера
    public Collection<CacheValue> getAll(){
        return cache.values();
    }

}
