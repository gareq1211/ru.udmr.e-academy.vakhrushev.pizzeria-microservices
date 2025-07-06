package com.pizzeria.order.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long clientId;
    private Long pizzaId;
}
