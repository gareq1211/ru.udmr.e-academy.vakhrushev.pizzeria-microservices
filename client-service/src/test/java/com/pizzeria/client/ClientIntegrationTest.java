//package com.pizzeria.client;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pizzeria.client.dto.ClientDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class ClientIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    void createAndGetClient() {
//        ClientDto client = new ClientDto(null, "Pavel", "123456789");
//
//        ResponseEntity<ClientDto> postResponse = restTemplate.postForEntity(
//                "http://localhost:" + port + "/clients", client, ClientDto.class);
//
//        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(postResponse.getBody()).isNotNull();
//        Long id = postResponse.getBody().getId();
//
//        ResponseEntity<ClientDto> getResponse = restTemplate.getForEntity(
//                "http://localhost:" + port + "/clients/" + id, ClientDto.class);
//
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(getResponse.getBody().getName()).isEqualTo("Pavel");
//    }
//}
//
