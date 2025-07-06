package com.pizzeria.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "pizzeria.services")
public class ServiceUrlsConfig {
    private String client;
    private String menu;
    private String kitchen;
}
