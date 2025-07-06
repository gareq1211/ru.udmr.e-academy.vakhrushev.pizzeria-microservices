package com.pizzeria.client;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.pizzeria.client.dto.ClientDto;
import com.pizzeria.shared.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping(ClientController.API_PATH)
public class ClientController {

    public static final String API_PATH = "/clients";

    private final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        return repository.findAll().stream()
                .map(c -> new ClientDto(c.getId(), c.getName(), c.getPhone()))
                .toList();
    }

    @PostMapping
    public ClientDto createClient(@Valid @RequestBody ClientDto dto) {
        Client client = new Client(dto.getName(), dto.getPhone());
        Client saved = repository.save(client);
        return new ClientDto(saved.getId(), saved.getName(), saved.getPhone());
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Клиент с ID " + id + " не найден"));
        return new ClientDto(client.getId(), client.getName(), client.getPhone());
    }
}
