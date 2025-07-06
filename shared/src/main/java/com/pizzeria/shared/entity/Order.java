package com.pizzeria.shared.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders", schema = "order_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private Long pizzaId;
    private String status;
}
