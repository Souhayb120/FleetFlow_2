package com.example.FleetFlow.services;

import com.example.FleetFlow.DTO.ClientDTO;
import com.example.FleetFlow.DTO.CreateClientDTO;
import com.example.FleetFlow.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    void ajouterClient(CreateClientDTO client);

    void deleteClient(int id);

    Page<ClientDTO> afficherClients(Pageable pageable);

    Client updateClient(int id, Client newData);
}