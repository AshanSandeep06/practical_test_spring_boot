package com.visionex_digital.spring_boot_test.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;import java.util.concurrent.TimeUnit;

/**
 * Title: CacheConfig Class
 * Description: Cache configuration class for caffeine caching
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .maximumSize(100)
                .recordStats();
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("weatherSummary");
//        cacheManager.setCaffeine(caffeineConfig());
        cacheManager.setCaffeine(caffeine);
        cacheManager.setAsyncCacheMode(true);
        return cacheManager;
    }
}
