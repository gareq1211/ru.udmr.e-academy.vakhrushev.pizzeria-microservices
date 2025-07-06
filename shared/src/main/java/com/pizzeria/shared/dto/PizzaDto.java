package com.pizzeria.shared.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDto {
    private Long id;
    private String name;
    private Double price;
}