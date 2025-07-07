package com.pizzeria.client.service;

import com.pizzeria.client.Client;
import com.pizzeria.client.repositirory.ClientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ClientService {
    private final ClientRepository clientRepository;
    public Client createClient(String name, String phone) {
        Optional<Client> existingClient = clientRepository.findByPhone(phone);
        if (existingClient.isPresent()) {
            Client client = existingClient.get();
            return client;
        }
        Client client = new Client(name,phone);
        return clientRepository.save(client);
    }
}


