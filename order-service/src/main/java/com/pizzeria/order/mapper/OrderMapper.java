package com.pizzeria.order.mapper;

import com.pizzeria.shared.dto.ClientDto;
import com.pizzeria.shared.dto.OrderDto;
import com.pizzeria.shared.dto.PizzaDto;
import com.pizzeria.shared.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order, PizzaDto pizza, ClientDto client) {
        return new OrderDto(
                order.getId(),
                client != null ? client.getName() : "Неизвестный клиент",
                pizza != null ? pizza.getName() : "Неизвестная пицца",
                order.getStatus()
        );
    }
}
