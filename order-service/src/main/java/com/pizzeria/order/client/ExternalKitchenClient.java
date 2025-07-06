package com.pizzeria.order.client;

import com.pizzeria.shared.dto.KitchenTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ExternalKitchenClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${kitchen.service.url}")
    private String kitchenServiceUrl;

    public void sendToKitchen(KitchenTaskRequest request) {
        webClientBuilder.build()
                .post()
                .uri(kitchenServiceUrl + "/kitchen-tasks")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(); // запускаем асинхронно
    }
}
