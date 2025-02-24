package com.company.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            template.header("Accept", "application/json");
            template.header("Connection", "keep-alive");
            template.header("Cache-Control", "no-cache");
        };
    }
}