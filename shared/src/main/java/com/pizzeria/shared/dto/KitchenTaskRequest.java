package com.pizzeria.shared.dto;

import lombok.Data;

@Data
public class KitchenTaskRequest {
    private Long orderId;
    private String description;
    private String status; // например, "WAITING"
}
