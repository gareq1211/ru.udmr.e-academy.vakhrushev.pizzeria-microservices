package com.pizzeria.shared.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String clientName;
    private String pizzaName;
    private String status;
}
