package com.example.FleetFlow.services;

import com.example.FleetFlow.DTO.CreateChauffeurDTO;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.repositories.ChauffeurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChauffeurServiceImplTest {

    @Autowired
    private ChauffeurService chauffeurService;

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Test
    void ajouterChauffeur() {
        CreateChauffeurDTO dto = new CreateChauffeurDTO();
        dto.setNom("Ali");
        dto.setPhone("123456");
        dto.setPermisType("B");
        
        chauffeurService.ajouterChauffeur(dto);
        
        assertEquals(1, chauffeurRepository.findAll().stream().filter(c -> c.getNom().equals("Ali")).count());
    }

    @Test
    void deleteChauffeur() {
        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setNom("To Delete");
        chauffeur = chauffeurRepository.save(chauffeur);
        int id = (int) chauffeur.getId();

        chauffeurService.deleteChauffeur(id);
        assertFalse(chauffeurRepository.existsById(id));
    }
}
