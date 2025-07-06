package com.pizzeria.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pizzeria.kitchen")
@EntityScan(basePackages = "com.pizzeria.shared.entity")
public class KitchenServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KitchenServiceApplication.class, args);
    }
}
