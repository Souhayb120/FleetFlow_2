package com.example.FleetFlow.services;

import com.example.FleetFlow.models.Vehicule;
import com.example.FleetFlow.repositories.VehiculeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class VehiculeServiceTest {

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Test
    void shouldReturnVehiculesByStatut() {
        String statut = "Disponible";
        Vehicule vehicule1 = new Vehicule();
        vehicule1.setMatricule("M1");
        vehicule1.setStatut(statut);
        vehiculeRepository.save(vehicule1);

        Vehicule vehicule2 = new Vehicule();
        vehicule2.setMatricule("M2");
        vehicule2.setStatut(statut);
        vehiculeRepository.save(vehicule2);

        Page<Vehicule> rs = vehiculeService.findByStatut(statut, PageRequest.of(0, 10));

        assertTrue(rs.getContent().stream().allMatch(v -> v.getStatut().equals(statut)));
        assertTrue(rs.getContent().size() >= 2);
    }

    @Test
    void shouldReturnVehiculesWithCapacityGreaterThan() {
        int capacity = 50;
        Vehicule vehicule1 = new Vehicule();
        vehicule1.setMatricule("M3");
        vehicule1.setCapacite(60);
        vehiculeRepository.save(vehicule1);

        Vehicule vehicule2 = new Vehicule();
        vehicule2.setMatricule("M4");
        vehicule2.setCapacite(80);
        vehiculeRepository.save(vehicule2);

        Page<Vehicule> rs = vehiculeService.findGreaterCapacityThan(capacity, PageRequest.of(0, 10));

        assertTrue(rs.getContent().stream().allMatch(v -> v.getCapacite() > capacity));
        assertTrue(rs.getContent().size() >= 2);
    }
}
