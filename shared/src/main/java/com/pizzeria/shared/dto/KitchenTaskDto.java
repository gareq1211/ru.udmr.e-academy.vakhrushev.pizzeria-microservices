package com.pizzeria.shared.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KitchenTaskDto {
    private Long id;
    private Long orderId;
    private String status;
    private String description;
}
