package com.pizzeria.order.client;

import com.pizzeria.order.config.ServiceUrlsConfig;
import com.pizzeria.shared.entity.KitchenTask;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.pizzeria.shared.dto.ClientDto;
import com.pizzeria.shared.dto.PizzaDto;
import com.pizzeria.shared.exception.NotFoundException;
import com.pizzeria.shared.exception.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final ServiceUrlsConfig serviceUrls;

    public ExternalServiceClient(WebClient.Builder webClientBuilder, ServiceUrlsConfig serviceUrls) {
        this.webClientBuilder = webClientBuilder;
        this.serviceUrls = serviceUrls;
    }

    public ClientDto getClient(Long clientId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(serviceUrls.getClient() + "/clients/" + clientId)
                    .retrieve()
                    .bodyToMono(ClientDto.class)
                    .blockOptional()
                    .orElseThrow(() -> new NotFoundException("Клиент с ID " + clientId + " не найден"));
        } catch (Exception e) {
            throw new BadRequestException("Ошибка при получении клиента: " + e.getMessage());
        }
    }



    public List<PizzaDto> getPizza(List<Long> pizzaIds) {
        try {
            // Преобразуем список ID в строку вида "1,2,3"
            String ids = pizzaIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            // Выполняем GET /menu?id=1,2,3
            return webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/menu?id=" + ids)
                    .retrieve()
                    .bodyToFlux(PizzaDto.class)
                    .collectList()
                    .block();
        } catch (Exception e) {
            throw new BadRequestException("Ошибка при получении пиццы: " + e.getMessage());
        }
    }

    public void createKitchenTask(String taskDescription) {
        try {
            KitchenTask task = new KitchenTask();
            task.setDescription(taskDescription);

            webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8083/kitchen-tasks")
                    .bodyValue(task)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            throw new BadRequestException("Не удалось создать задачу на кухне: " + e.getMessage());
        }
    }
}
