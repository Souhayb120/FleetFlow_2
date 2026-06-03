package com.example.FleetFlow.services;

import com.example.FleetFlow.DTO.ChauffeurDTO;
import com.example.FleetFlow.DTO.CreateChauffeurDTO;
import com.example.FleetFlow.DTO.CreateClientDTO;
import com.example.FleetFlow.Mapper.ChauffeurMapper;
import com.example.FleetFlow.Mapper.ClientMapper;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.models.Client;
import com.example.FleetFlow.repositories.ChauffeurRepository;
import com.example.FleetFlow.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChauffeurServiceImplTest {


    @InjectMocks
    private ChauffeurService chauffeurService;
    @Mock
    private ChauffeurMapper mapper;
    @Mock
    private ChauffeurRepository chauffeurRepository;

    private ChauffeurDTO chauffeurDTO;
    private Chauffeur chauffeur;

    @org.junit.jupiter.api.BeforeEach
    public void setUp(){
        chauffeurDTO = new ChauffeurDTO();
        chauffeurDTO.setNom("Ali");
        chauffeur = new Chauffeur();
        chauffeur.setNom("Ali");
    }


    @Test
    void findByDisponibility() {
        when(chauffeurRepository.findByIsDisponibleTrue()).thenReturn(List.of(chauffeur));
        when(mapper.toDTO(chauffeur)).thenReturn(chauffeurDTO);
        List<ChauffeurDTO> result = chauffeurService.findByDisponibility();
        assertNotNull(result);
        verify(chauffeurRepository, times(1)).findByIsDisponibleTrue();
        verify(mapper, times(1)).toDTO(chauffeur);
    }
}