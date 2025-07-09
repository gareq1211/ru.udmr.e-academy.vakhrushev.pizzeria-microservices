package com.pizzeria.order.mapper;

import com.pizzeria.shared.dto.ClientDto;
import com.pizzeria.shared.dto.OrderDto;
import com.pizzeria.shared.dto.PizzaDto;
import com.pizzeria.shared.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order, List<PizzaDto> pizzas, ClientDto client) {
        List<String> pizzaNames = pizzas != null
                ? pizzas.stream().map(PizzaDto::getName).toList()
                : List.of("Неизвестная пицца");

        return new OrderDto(
                order.getId(),
                client != null ? client.getName() : "Неизвестный клиент",
                pizzaNames,
                order.getStatus()
        );
    }
}


