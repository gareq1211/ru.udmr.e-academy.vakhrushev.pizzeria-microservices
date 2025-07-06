package com.pizzeria.order.client;

import com.pizzeria.shared.dto.PizzaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ExternalMenuClientTest {

    private WebClient.Builder webClientBuilder;
    private WebClient webClient;
    private WebClient.RequestHeadersUriSpec request;
    private WebClient.RequestHeadersSpec headers;
    private WebClient.ResponseSpec response;

    private ExternalMenuClient client;

    @BeforeEach
    void setup() {
        webClientBuilder = mock(WebClient.Builder.class);
        webClient = mock(WebClient.class);
        request = mock(WebClient.RequestHeadersUriSpec.class);
        headers = mock(WebClient.RequestHeadersSpec.class);
        response = mock(WebClient.ResponseSpec.class);

        when(webClientBuilder.build()).thenReturn(webClient);
        client = new ExternalMenuClient(webClientBuilder);
        client.menuServiceUrl = "http://localhost:8081";
    }

    @Test
    void getPizzaById_shouldReturnPizza() {
        PizzaDto pizza = new PizzaDto(1L, "Маргарита", 450.0);

        when(webClient.get()).thenReturn(request);
        when(request.uri("http://localhost:8081/menu/1")).thenReturn(headers);
        when(headers.retrieve()).thenReturn(response);
        when(response.bodyToMono(PizzaDto.class)).thenReturn(Mono.just(pizza));

        PizzaDto result = client.getPizzaById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Маргарита");
    }

    @Test
    void getPizzaById_shouldReturnNull_whenError() {
        when(webClient.get()).thenReturn(request);
        when(request.uri("http://localhost:8081/menu/99")).thenThrow(new RuntimeException("Ошибка"));

        PizzaDto result = client.getPizzaById(99L);

        assertThat(result).isNull();
    }
}
