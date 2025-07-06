package com.pizzeria.kitchen;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pizzeria.shared.entity.KitchenTask;

public interface KitchenTaskRepository extends JpaRepository<KitchenTask, Long> {
}
