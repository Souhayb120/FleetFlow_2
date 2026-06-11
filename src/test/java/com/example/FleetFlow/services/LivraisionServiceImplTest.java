package com.example.FleetFlow.services;

import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.models.Livraison;
import com.example.FleetFlow.models.Vehicule;
import com.example.FleetFlow.repositories.ChauffeurRepository;
import com.example.FleetFlow.repositories.LivraisonRepository;
import com.example.FleetFlow.repositories.VehiculeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LivraisionServiceImplTest {

    @Autowired
    private LivraisionService livraisionService;

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Test
    void shouldCreateLivraisonWithInitialStatus() {
        Livraison livraison = new Livraison();
        livraisionService.creeLivraision(livraison);
        assertEquals("EN_ATTENTE", livraison.getStatut());
    }

    @Test
    void shouldAAssignerChauffeurVehiculeAuneLivraison() {
        // GIVEN
        Livraison livraison = new Livraison();
        livraison = livraisonRepository.save(livraison);
        
        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setNom("Driver");
        chauffeur.setIsDisponible(true);
        chauffeur = chauffeurRepository.save(chauffeur);
        
        Vehicule vehicule = new Vehicule();
        vehicule.setMatricule("V123");
        vehicule.setStatut("Disponible");
        vehicule = vehiculeRepository.save(vehicule);

        // WHEN
        Livraison rs = livraisionService.assigner(livraison.getId(), (int) chauffeur.getId(), vehicule.getId());

        // THEN
        assertNotNull(rs.getChauffeur());
        assertEquals(chauffeur.getId(), rs.getChauffeur().getId());
        assertNotNull(rs.getVehicule());
        assertEquals(vehicule.getId(), rs.getVehicule().getId());
        assertEquals("ENCOURS", rs.getStatut());
        assertEquals("Occuppier", rs.getVehicule().getStatut());
        assertFalse(rs.getChauffeur().getIsDisponible());
    }

    @Test
    void shouldUpdateLivraisonStatus() {
        // GIVEN
        Livraison livraison = new Livraison();
        livraison.setStatut("EN_ATTENTE");
        livraison = livraisonRepository.save(livraison);

        String newStatut = "ENCOURS";

        // WHEN
        Livraison rs = livraisionService.updateStatut(livraison.getId(), newStatut);

        // THEN
        assertEquals(newStatut, rs.getStatut());
    }
}
