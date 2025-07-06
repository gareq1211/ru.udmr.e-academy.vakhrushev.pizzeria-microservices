package com.pizzeria.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Главный класс запуска сервиса меню.
 */
@SpringBootApplication
@EntityScan(basePackages = "com.pizzeria.menu.entity")
@EnableJpaRepositories(basePackages = "com.pizzeria.menu.repository")
public class MenuServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuServiceApplication.class, args);
    }
}
