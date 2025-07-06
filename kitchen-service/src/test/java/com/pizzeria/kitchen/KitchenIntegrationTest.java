// src/test/java/com/pizzeria/kitchen/KitchenIntegrationTest.java

package com.pizzeria.kitchen;

import com.pizzeria.shared.entity.KitchenTask;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KitchenIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/kitchen-tasks";
    }

    @Test
    void createAndGetAllTasks() {
        KitchenTask task = new KitchenTask();
        task.setOrderId(1L);
        task.setDescription("Сделать пиццу");
        task.setStatus("PENDING");

        ResponseEntity<KitchenTask> postResponse = restTemplate.postForEntity(baseUrl(), task, KitchenTask.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponse.getBody()).isNotNull();

        ResponseEntity<KitchenTask[]> getResponse = restTemplate.getForEntity(baseUrl(), KitchenTask[].class);
        List<KitchenTask> tasks = Arrays.asList(getResponse.getBody());

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(tasks).isNotEmpty();
    }

    @Test
    void updateTaskStatus() {
        KitchenTask task = new KitchenTask();
        task.setOrderId(2L);
        task.setDescription("Сделать роллы");
        task.setStatus("PENDING");

        KitchenTask created = restTemplate.postForEntity(baseUrl(), task, KitchenTask.class).getBody();
        assertThat(created).isNotNull();
        Long id = created.getId();

        StatusUpdateDto dto = new StatusUpdateDto("READY");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StatusUpdateDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<KitchenTask> updated = restTemplate.exchange(
                baseUrl() + "/" + id + "/status", HttpMethod.PUT, entity, KitchenTask.class);

        assertThat(updated.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updated.getBody()).isNotNull();
        assertThat(updated.getBody().getStatus()).isEqualTo("READY");
    }

    @Data
    static class StatusUpdateDto {
        private final String status;
    }
}
