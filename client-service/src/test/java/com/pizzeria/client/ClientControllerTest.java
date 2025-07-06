package com.pizzeria.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzeria.client.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllClients() throws Exception {
        Client client = new Client(1L, "Иван", "+79991234567");
        when(clientRepository.findAll()).thenReturn(List.of(client));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Иван"));
    }

    @Test
    void shouldReturnClientById() throws Exception {
        Client client = new Client(1L, "Анна", "89997776655");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phone").value("89997776655"));
    }

    @Test
    void shouldReturn404WhenClientNotFound() throws Exception {
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/clients/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Клиент с ID 99 не найден"));
    }

    @Test
    void shouldCreateClient() throws Exception {
        Client client = new Client(null, "Олег", "89001112233");
        Client savedClient = new Client(1L, "Олег", "89001112233");

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldFailValidationWhenNameMissing() throws Exception {
        Client client = new Client(null, "", "89994443322");

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Имя клиента не должно быть пустым"));

    }
}
