package com.pizzeria.shared.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String clientName;
    private List<String> pizzaName;
    private String status;
}
