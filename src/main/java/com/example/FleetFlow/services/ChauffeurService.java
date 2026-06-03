package com.example.FleetFlow.services;


import com.example.FleetFlow.DTO.ChauffeurDTO;
import com.example.FleetFlow.DTO.CreateChauffeurDTO;
import com.example.FleetFlow.Mapper.ChauffeurMapper;
import com.example.FleetFlow.models.Chauffeur;

import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;


public interface ChauffeurService {

    void ajouterChauffeur(CreateChauffeurDTO chauffeur);

    void deleteChauffeur(int id);

    Page<ChauffeurDTO> displayChauffeurs(org.springframework.data.domain.Pageable pageable);

    Chauffeur updateChauffeur(int id, Chauffeur newData);

    Page<ChauffeurDTO> findByDisponibility(Pageable pageable);

    Page<ChauffeurDTO> findByPermisTypeDisponible(
            String permisType,
            Boolean isDisponible,
            Pageable pageable
    );

    Page<String> displayChauffeursByNom(Pageable pageable);
}
