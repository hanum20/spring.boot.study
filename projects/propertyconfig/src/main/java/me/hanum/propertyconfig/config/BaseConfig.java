package me.hanum.propertyconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Profile("prod")
@Configuration
public class BaseConfig {

    @Bean
    public String hello() {
        return "hello";
    }
}

