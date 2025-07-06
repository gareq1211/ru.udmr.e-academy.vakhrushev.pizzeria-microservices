package com.pizzeria.menu;

import com.pizzeria.menu.entity.Pizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.yml")
public class MenuIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/menu"; // совпадает с base-path из application-test.yml
    }

    @Test
    void createAndGetPizza() {
        Pizza pizza = new Pizza(null, "Маргарита", 499.0);

        ResponseEntity<Pizza> createResponse = restTemplate.postForEntity(
                getBaseUrl(), pizza, Pizza.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Pizza created = createResponse.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Маргарита");

        ResponseEntity<Pizza> getById = restTemplate.getForEntity(
                getBaseUrl() + "/" + created.getId(), Pizza.class);

        assertThat(getById.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getById.getBody()).isNotNull();
        assertThat(getById.getBody().getName()).isEqualTo("Маргарита");
    }

    @Test
    void getAllPizzas() {
        Pizza pizza = new Pizza(null, "Пепперони", 599.0);
        restTemplate.postForEntity(getBaseUrl(), pizza, Pizza.class);

        ResponseEntity<Pizza[]> response = restTemplate.getForEntity(getBaseUrl(), Pizza[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }
    @Test
    void updatePizza() {
        // Сначала создаём пиццу
        Pizza original = new Pizza(null, "Четыре сыра", 699.0);
        Pizza created = restTemplate.postForEntity(getBaseUrl(), original, Pizza.class).getBody();

        // Меняем имя и цену
        created.setName("Пицца с грибами");
        created.setPrice(749.0);

        HttpEntity<Pizza> request = new HttpEntity<>(created);
        ResponseEntity<Pizza> updateResponse = restTemplate.exchange(
                getBaseUrl() + "/" + created.getId(),
                HttpMethod.PUT,
                request,
                Pizza.class
        );

        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Pizza updated = updateResponse.getBody();
        assertThat(updated.getName()).isEqualTo("Пицца с грибами");
        assertThat(updated.getPrice()).isEqualTo(749.0);
    }

    @Test
    void deletePizza() {
        Pizza pizza = new Pizza(null, "Гавайская", 599.0);
        Pizza created = restTemplate.postForEntity(getBaseUrl(), pizza, Pizza.class).getBody();

        restTemplate.delete(getBaseUrl() + "/" + created.getId());

        ResponseEntity<String> getResponse = restTemplate.getForEntity(
                getBaseUrl() + "/" + created.getId(), String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createPizza_validationError() {
        Pizza invalid = new Pizza(null, "", 500.0);

        ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl(), invalid, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Название пиццы не должно быть пустым");
    }
}

