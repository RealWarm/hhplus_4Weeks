package com.hoonterpark.concertmanager.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@EnableAsync
@Configuration
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); // 기본 스레드 수
        executor.setMaxPoolSize(100); // 최대 스레드 수
        executor.setQueueCapacity(5); // 큐 용량
        executor.setThreadNamePrefix("Async-"); // 스레드 이름 접두사
        executor.initialize();
        return executor;
    }
}