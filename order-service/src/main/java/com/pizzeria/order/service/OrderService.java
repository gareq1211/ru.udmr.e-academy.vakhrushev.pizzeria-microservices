package com.pizzeria.order.service;

import com.pizzeria.order.repository.OrderRepository;
import com.pizzeria.order.client.ExternalServiceClient;
import com.pizzeria.order.mapper.OrderMapper;
import com.pizzeria.shared.dto.ClientDto;
import com.pizzeria.shared.dto.OrderDto;
import com.pizzeria.shared.dto.PizzaDto;
import com.pizzeria.shared.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ExternalServiceClient externalServiceClient;
    private final OrderMapper mapper;

    public List<OrderDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public OrderDto create(Order order) {
        List<PizzaDto> pizzas = externalServiceClient.getPizza(order.getPizzaId());
        ClientDto client = externalServiceClient.getClient(order.getClientId());
        Order saved = repository.save(order);
        externalServiceClient.createKitchenTask("Приготовить заказ №" + saved.getId(), saved.getId());
        return mapper.toDto(saved, pizzas, client);
    }

    public OrderDto updateStatus(Long id, String status) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Заказ не найден"));
        if (!isValidStatus(status)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Недопустимый статус: " + status);
        }
        order.setStatus(status);
        Order updated = repository.save(order);
        return toDto(updated);
    }


    private OrderDto toDto(Order order) {
        List<PizzaDto> pizzas = externalServiceClient.getPizza(
                order.getPizzaId()
        );
        ClientDto client = externalServiceClient.getClient(order.getClientId());
        return mapper.toDto(order, pizzas, client);
    }

    private boolean isValidStatus(String status) {
        return List.of("WAITING", "READY", "CANCELLED").contains(status.toUpperCase());
    }

    public OrderDto toOrderDto(Order order) {
        List<PizzaDto> pizza = externalServiceClient.getPizza(
                order.getPizzaId()
        );
        ClientDto client = externalServiceClient.getClient(order.getClientId());
        return new OrderDto(
                order.getId(),
                client != null ? client.getName() : "Неизвестный клиент",
                pizza != null ? pizza.stream().map(PizzaDto::getName).toList() : List.of("Неизвестные пиццы"),
                order.getStatus()
        );
    }

}

