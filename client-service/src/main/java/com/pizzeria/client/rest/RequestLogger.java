package com.pizzeria.client.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class RequestLogger {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    public void log(Object request) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String jsonString = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: {}", jsonString);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON:", e);
        }
    }
}
