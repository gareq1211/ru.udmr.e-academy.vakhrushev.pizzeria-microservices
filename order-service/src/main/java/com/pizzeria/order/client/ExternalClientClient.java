package com.pizzeria.order.client;

import com.pizzeria.shared.dto.ClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ExternalClientClient {

    private final WebClient.Builder webClientBuilder;
    private final String clientServiceUrl;

    public ExternalClientClient(WebClient.Builder webClientBuilder,
                                @Value("${client.service.url}") String clientServiceUrl) {
        this.webClientBuilder = webClientBuilder;
        this.clientServiceUrl = clientServiceUrl;
    }

    public ClientDto getClientById(Long clientId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(clientServiceUrl + "/clients/" + clientId)
                    .retrieve()
                    .bodyToMono(ClientDto.class)
                    .block();
        } catch (Exception e) {
            System.err.println("Ошибка при получении клиента с ID " + clientId + ": " + e.getMessage());
            return null;
        }
    }
}
