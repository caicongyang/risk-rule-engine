package com.caicongyang.risk.rule.engine.server.conf;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Value("${performanceInterceptor.enable:false}")
    private boolean enable;
    @Value("${performanceInterceptor.maxTime:2000}")
    private Integer maxTime;


    public MybatisPlusConfig() {
    }


    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        if (this.enable) {
            PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
            performanceInterceptor.setMaxTime((long) this.maxTime);
            performanceInterceptor.setFormat(true);
            return performanceInterceptor;
        } else {
            return null;
        }
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}