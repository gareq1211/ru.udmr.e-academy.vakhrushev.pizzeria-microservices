package com.pizzeria.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String clientName;
    private List<String> pizzaNames;
    private String status;
}
