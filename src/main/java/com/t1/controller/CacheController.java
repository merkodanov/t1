package com.t1.controller;

import com.t1.infrastucture.CacheManager;
import com.t1.infrastucture.CacheValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


//Контроллер для проверки содержимого в кеше
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {
    private final CacheManager cacheManager;

    @GetMapping
    public ResponseEntity<Collection<CacheValue>> getCache() {
        return ResponseEntity.ok(cacheManager.getAll());
    }

}
