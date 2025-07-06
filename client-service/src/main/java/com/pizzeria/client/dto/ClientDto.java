package com.pizzeria.client.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;

    @NotBlank(message = "Имя клиента не должно быть пустым")
    private String name;

    @NotBlank(message = "Номер телефона обязателен")
    private String phone;
}