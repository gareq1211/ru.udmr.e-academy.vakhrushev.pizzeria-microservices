//package com.pizzeria.order.client;
//
//import com.pizzeria.shared.dto.ClientDto;
//import com.pizzeria.shared.dto.PizzaDto;
//import com.pizzeria.shared.entity.KitchenTask;
//import com.pizzeria.shared.exception.BadRequestException;
//import com.pizzeria.shared.exception.NotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class ExternalServiceClientTest {
//
//    private WebClient.Builder webClientBuilder;
//    private WebClient webClient;
//    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;
//    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;
//    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
//    private WebClient.RequestBodySpec requestBodySpec;
//    private WebClient.ResponseSpec responseSpec;
//
//    private ExternalServiceClient serviceClient;
//
//    @BeforeEach
//    void setup() {
//        webClientBuilder = mock(WebClient.Builder.class);
//        webClient = mock(WebClient.class);
//        requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
//        requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
//        requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
//        requestBodySpec = mock(WebClient.RequestBodySpec.class);
//        responseSpec = mock(WebClient.ResponseSpec.class);
//
//        when(webClientBuilder.build()).thenReturn(webClient);
//        serviceClient = new ExternalServiceClient(webClientBuilder, new StubServiceUrlsConfig());
//    }
//
//    @Test
//    void getClient_shouldReturnClient() {
//        ClientDto expected = new ClientDto(1L, "Иван", "123");
//
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri("http://localhost:8082/clients/1")).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(ClientDto.class)).thenReturn(Mono.just(expected));
//
//        ClientDto actual = serviceClient.getClient(1L);
//
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    void getPizza_shouldReturnPizza() {
//        PizzaDto expected = new PizzaDto(1L, "Маргарита", 450.0);
//
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri("http://localhost:8081/menu/1")).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(PizzaDto.class)).thenReturn(Mono.just(expected));
//
//        PizzaDto actual = serviceClient.getPizza(1L);
//
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    void createKitchenTask_shouldSendRequest() {
//        when(webClient.post()).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.uri("http://localhost:8084/kitchen-tasks")).thenReturn(requestBodySpec);
//        when(requestBodySpec.bodyValue(any(KitchenTask.class))).thenReturn(requestBodySpec);
//        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.toBodilessEntity()).thenReturn(Mono.just(ResponseEntity.ok().build()));
//
//        assertThatCode(() -> serviceClient.createKitchenTask("Задача")).doesNotThrowAnyException();
//    }
//
//    @Test
//    void getClient_shouldThrowNotFound() {
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri("http://localhost:8082/clients/999")).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(ClientDto.class)).thenReturn(Mono.empty());
//
//        assertThatThrownBy(() -> serviceClient.getClient(999L))
//                .isInstanceOf(NotFoundException.class)
//                .hasMessageContaining("не найден");
//    }
//
//    @Test
//    void getPizza_shouldThrowBadRequest() {
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri("http://localhost:8081/menu/5"))
//                .thenThrow(new RuntimeException("Ошибка соединения"));
//
//        assertThatThrownBy(() -> serviceClient.getPizza(5L))
//                .isInstanceOf(BadRequestException.class)
//                .hasMessageContaining("Ошибка при получении пиццы");
//    }
//
//    static class StubServiceUrlsConfig extends com.pizzeria.order.config.ServiceUrlsConfig {
//        public StubServiceUrlsConfig() {
//            setClient("http://localhost:8082");
//            setMenu("http://localhost:8081");
//            setKitchen("http://localhost:8084");
//        }
//    }
//}
