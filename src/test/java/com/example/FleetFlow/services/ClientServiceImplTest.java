package com.example.FleetFlow.services;

import com.example.FleetFlow.DTO.CreateClientDTO;
import com.example.FleetFlow.models.Client;
import com.example.FleetFlow.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientServiceImplTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    private CreateClientDTO clientDTO;

    @BeforeEach
    public void setUp() {
        clientDTO = new CreateClientDTO("ayoub", "ayoub@gmail.com", "5634534");
    }

    @Test
    void ajouterClient() {
        clientService.ajouterClient(clientDTO);
        assertTrue(clientRepository.existsByEmail(clientDTO.getEmail()));
    }

    @Test
    void ajouterClientCheckEmailExists() {
        Client existingClient = new Client();
        existingClient.setNom("Existing");
        existingClient.setEmail(clientDTO.getEmail());
        clientRepository.save(existingClient);

        clientService.ajouterClient(clientDTO);
        
        // Should still only have 1 client with this email (the existing one)
        // Note: The service implementation doesn't throw exception, just doesn't save.
        long count = clientRepository.findAll().stream().filter(c -> c.getEmail().equals(clientDTO.getEmail())).count();
        assertEquals(1, count);
    }

    @Test
    void deleteClient() {
        Client client = new Client();
        client.setNom("To Delete");
        client.setEmail("delete@test.com");
        client = clientRepository.save(client);
        int id = (int) client.getId();

        clientService.deleteClient(id);
        assertFalse(clientRepository.existsById(id));
    }
}
