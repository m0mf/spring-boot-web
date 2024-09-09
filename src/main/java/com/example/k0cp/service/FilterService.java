package com.example.k0cp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    private final Logger logger = LoggerFactory.getLogger(FilterService.class);

    @Async
    public void executeService() {
        logger.info("Execute FilterService");
    }
}
