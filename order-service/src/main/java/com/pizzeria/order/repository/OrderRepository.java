package com.pizzeria.order.repository;

import com.pizzeria.shared.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
