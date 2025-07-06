package com.pizzeria.order.client;

import com.pizzeria.shared.dto.PizzaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ExternalMenuClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${menu.service.url}")
    String menuServiceUrl;

    public ExternalMenuClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public PizzaDto getPizzaById(Long pizzaId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(menuServiceUrl + "/menu/" + pizzaId)
                    .retrieve()
                    .bodyToMono(PizzaDto.class)
                    .block();
        } catch (Exception e) {
            System.err.println("Ошибка при получении пиццы с ID " + pizzaId + ": " + e.getMessage());
            return null;
        }
    }
}
