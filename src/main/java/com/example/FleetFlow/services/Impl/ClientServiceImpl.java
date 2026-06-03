package com.example.FleetFlow.services.Impl;

import com.example.FleetFlow.DTO.ClientDTO;
import com.example.FleetFlow.DTO.CreateClientDTO;
import com.example.FleetFlow.Mapper.ClientMapper;
import com.example.FleetFlow.models.Client;
import com.example.FleetFlow.repositories.ClientRepository;
import com.example.FleetFlow.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    public ClientServiceImpl(
            ClientRepository clientRepository,
            ClientMapper mapper
    ) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public void ajouterClient(CreateClientDTO client) {

        if (!clientRepository.existsByEmail(client.getEmail())) {
            clientRepository.save(mapper.toEntity(client));
        }
    }

    @Override
    public void deleteClient(int id) {

        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        }
    }

    @Override
    public Page<ClientDTO> afficherClients(Pageable pageable) {

        return clientRepository.findAll(pageable)
                .map(client -> {
                    ClientDTO dto = mapper.toDTO(client);
                    dto.setNombreLivraison(
                            client.getLivraisonList().size()
                    );
                    return dto;
                });
    }

    @Override
    public Client updateClient(int id, Client newData) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Client not found"));

        client.setNom(newData.getNom());
        client.setEmail(newData.getEmail());
        client.setPhone(newData.getPhone());

        return clientRepository.save(client);
    }
}