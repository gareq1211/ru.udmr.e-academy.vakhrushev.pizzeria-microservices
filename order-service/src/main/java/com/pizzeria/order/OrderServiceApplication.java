package com.pizzeria.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pizzeria.order")
@EntityScan(basePackages = "com.pizzeria.shared.entity")
@ConfigurationPropertiesScan(basePackages = "com.pizzeria.order.config")
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    /**
     * WebClient builder bean used for making HTTP requests to other microservices.
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
