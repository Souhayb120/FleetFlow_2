package com.example.FleetFlow.services;

import com.example.FleetFlow.DTO.VehiculeDTO;
import com.example.FleetFlow.models.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehiculeService {

    Vehicule ajouterVehicule(VehiculeDTO vehiculeDTO);

    Vehicule modifierVehicule(Long id, Vehicule vehicule);

    void supprimerVehicule(Long id);

    Page<VehiculeDTO> listerVehicule(Pageable pageable);

    Page<Vehicule> findByStatut(String statut, Pageable pageable);

    Page<Vehicule> findGreaterCapacityThan(int capacity, Pageable pageable);
}