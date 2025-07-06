package com.pizzeria.shared.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private String name;
    private String phone;
}
