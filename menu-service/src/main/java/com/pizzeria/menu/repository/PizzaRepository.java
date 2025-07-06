package com.pizzeria.menu.repository;

import com.pizzeria.menu.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
