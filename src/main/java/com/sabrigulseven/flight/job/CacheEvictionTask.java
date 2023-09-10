package com.sabrigulseven.flight.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictionTask {
    private static final Logger logger = LoggerFactory.getLogger(CacheEvictionTask.class);

    @Scheduled(cron = "0 0 0 * * ?")
    @CacheEvict(value = "airports", allEntries = true)
    public void evictAirportCache() {
        logger.info("Airport cache evicted at midnight.");
    }
}
