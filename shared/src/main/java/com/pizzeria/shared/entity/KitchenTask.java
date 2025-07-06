package com.pizzeria.shared.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "kitchen_tasks", schema = "kitchen_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KitchenTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @NotBlank(message = "Статус обязателен")
    private String status;
    @NotBlank(message = "Описание задачи обязательно")
    private String description;
}
