package com.cloud.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignClientConfiguration {

    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }
}
