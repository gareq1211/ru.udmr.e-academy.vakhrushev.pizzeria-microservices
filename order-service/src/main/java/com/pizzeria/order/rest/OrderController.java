package com.pizzeria.order.rest;

import com.pizzeria.order.dto.OrderRequest;
import com.pizzeria.order.repository.OrderRepository;
import com.pizzeria.order.dto.OrderStatusUpdateRequest;
import com.pizzeria.order.service.OrderService;
import com.pizzeria.shared.dto.OrderDto;
import com.pizzeria.shared.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository repository;
    private final RequestLogger requestLogger;
    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@RequestBody OrderRequest order) {
       requestLogger.log(order);
        Order newOrder = new Order();
        newOrder.setClientId(order.getClientId());
        newOrder.setPizzaId(order.getPizzaId()); // Теперь это список
        newOrder.setStatus("NEW");
        return orderService.create(newOrder);
    }

    @PutMapping("/{id}/status")
    public OrderDto updateStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateRequest request) {
        return orderService.updateStatus(id, request.getStatus());
    }
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Заказ не найден"));

        return orderService.toOrderDto(order);

    }
}
