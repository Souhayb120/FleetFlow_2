package com.example.FleetFlow.services;

import com.example.FleetFlow.DTO.CreateClientDTO;
import com.example.FleetFlow.Mapper.ClientMapper;
import com.example.FleetFlow.models.Client;
import com.example.FleetFlow.repositories.ClientRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

  
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientMapper mapper;
    @Mock
    private ClientRepository clientRepository;

    private CreateClientDTO clientDTO;
    private Client client;

    @org.junit.jupiter.api.BeforeEach
    public void setUp(){
        clientDTO = new CreateClientDTO("ayoub","ayoub@gmail.com","5634534");
        client = new Client();
    }


    @Test
    void ajouterClient() {
        when(mapper.toEntity(clientDTO)).thenReturn(client);
        when(clientRepository.existsByEmail(clientDTO.getEmail())).thenReturn(false);
        clientService.ajouterClient(clientDTO);
        verify(mapper, times(1)).toEntity(clientDTO);
        verify(clientRepository, times(1)).save(client);
}

    @Test
    void ajouterClientCheckEmailExists() {
        when(clientRepository.existsByEmail(clientDTO.getEmail())).thenReturn(true);
        clientService.ajouterClient(clientDTO);
        verify(clientRepository, never()).save(any());
    }


    @Test
    void deleteClient() {
        when(clientRepository.existsById(1)).thenReturn(true);
        clientService.deleteClient(1);
        verify(clientRepository, times(1)).deleteById(1);
    }
}