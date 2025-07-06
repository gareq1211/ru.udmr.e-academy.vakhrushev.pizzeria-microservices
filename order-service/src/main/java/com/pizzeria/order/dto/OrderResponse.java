package com.pizzeria.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String clientName;
    private String pizzaName;
    private String status;
}
