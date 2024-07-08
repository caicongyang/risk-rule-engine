package com.caicongyang.risk.rule.engine.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {
    /**
     * Set the ThreadPoolExecutor's core pool size.
     */
    private static final int CORE_POOL_SIZE = 8;
    /**
     * Set the ThreadPoolExecutor's maximum pool size.
     */
    private final static int MAX_POOL_SIZE = 32;

    @Bean
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        return executor;
    }

    @Bean("asyncExecutor")
    public ExecutorService asyncExecutor() {
        return asyncTaskExecutor().getThreadPoolExecutor();
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncTaskExecutor();
    }

}
