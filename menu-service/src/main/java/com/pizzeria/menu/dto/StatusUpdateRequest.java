package com.pizzeria.menu.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateRequest {

    @NotBlank(message = "Статус не может быть пустым")
    private String status;
}
