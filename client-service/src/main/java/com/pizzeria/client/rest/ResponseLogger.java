package com.pizzeria.client.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pizzeria.client.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class ResponseLogger{
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    public void log(Object response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String jsonString = objectMapper.writeValueAsString(response);
            logger.info("RESPONSE: {}", jsonString);
        } catch (JsonProcessingException e) {
            logger.error("Error converting response to JSON: ", e);
        }
    }
}