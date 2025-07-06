package com.pizzeria.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    private Long id;
    private String name;
    private double price;
}
