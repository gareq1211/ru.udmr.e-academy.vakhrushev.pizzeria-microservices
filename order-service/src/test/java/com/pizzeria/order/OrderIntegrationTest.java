//package com.pizzeria.order;
//
//import com.pizzeria.order.client.ExternalServiceClient;
//import com.pizzeria.shared.dto.ClientDto;
//import com.pizzeria.shared.dto.OrderDto;
//import com.pizzeria.shared.dto.PizzaDto;
//import com.pizzeria.shared.entity.Order;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class OrderIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @MockBean
//    private ExternalServiceClient externalServiceClient;
//
//    private static final Long TEST_PIZZA_ID = 1L;
//    private static final Long TEST_CLIENT_ID = 2L;
//    private static final String TEST_PIZZA_NAME = "Маргарита";
//    private static final String TEST_CLIENT_NAME = "Иван";
//    private static final String TEST_ORDER_STATUS = "WAITING";
//
//    private String baseUrl() {
//        return "http://localhost:" + port + "/orders";
//    }
//
//    @BeforeEach
//    void setUpMocks() {
//        when(externalServiceClient.getPizza(TEST_PIZZA_ID))
//                .thenReturn(new PizzaDto(TEST_PIZZA_ID, TEST_PIZZA_NAME, 499.0));
//
//        when(externalServiceClient.getClient(TEST_CLIENT_ID))
//                .thenReturn(new ClientDto(TEST_CLIENT_ID, TEST_CLIENT_NAME, "89998887766"));
//
//        doNothing().when(externalServiceClient).createKitchenTask(any());
//    }
//
//    @Test
//    void createOrder_success() {
//        // given
//        Order newOrder = new Order(null, TEST_CLIENT_ID, TEST_PIZZA_ID, TEST_ORDER_STATUS);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Order> request = new HttpEntity<>(newOrder, headers);
//
//        // when
//        ResponseEntity<OrderDto> response = restTemplate.postForEntity(baseUrl(), request, OrderDto.class);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//        OrderDto saved = response.getBody();
//        assertThat(saved).isNotNull();
//        assertThat(saved.getStatus()).isEqualTo(TEST_ORDER_STATUS);
//        assertThat(saved.getClientName()).isEqualTo(TEST_CLIENT_NAME);
//        assertThat(saved.getPizzaName()).isEqualTo(TEST_PIZZA_NAME);
//    }
//
//    @Test
//    void getAllOrders_success() {
//        // Arrange: создать заказ
//        Order newOrder = new Order(null, TEST_CLIENT_ID, TEST_PIZZA_ID, TEST_ORDER_STATUS);
//        restTemplate.postForEntity(baseUrl(), newOrder, OrderDto.class);
//
//        // Act
//        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl(), String.class);
//
//        // Assert
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains(TEST_PIZZA_NAME);
//        assertThat(response.getBody()).contains(TEST_CLIENT_NAME);
//    }
//}
