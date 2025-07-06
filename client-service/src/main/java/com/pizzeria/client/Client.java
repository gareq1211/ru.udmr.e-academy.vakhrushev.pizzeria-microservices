package com.pizzeria.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "clients", schema = "client_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя клиента не должно быть пустым")
    private String name;

    @NotBlank(message = "Номер телефона обязателен")
    private String phone;
    public Client(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}