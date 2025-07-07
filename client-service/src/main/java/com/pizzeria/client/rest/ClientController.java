package com.pizzeria.client.rest;

import com.pizzeria.client.Client;
import com.pizzeria.client.repositirory.ClientRepository;
import com.pizzeria.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.pizzeria.client.dto.ClientDto;
import com.pizzeria.shared.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping(ClientController.API_PATH)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ClientController {

    public static final String API_PATH = "/clients";

    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final RequestLogger requestLogger;
    private final ResponseLogger responseLogger;
    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(c -> new ClientDto(c.getId(), c.getName(), c.getPhone()))
                .toList();
    }

    @PostMapping
    public ClientDto createClient(@Valid @RequestBody ClientDto dto) {
        requestLogger.log(dto);
        Client client = clientService.createClient(dto.getName(), dto.getPhone());
        ClientDto clientDto = new ClientDto(client.getId(), client.getName(), client.getPhone());
        responseLogger.log(clientDto);
        return clientDto;
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Клиент с ID " + id + " не найден"));
        return new ClientDto(client.getId(), client.getName(), client.getPhone());
    }
}
