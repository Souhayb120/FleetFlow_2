package com.example.FleetFlow.repositories;

import com.example.FleetFlow.models.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehculeRepository extends JpaRepository<Vehicule, Long> {

    Page<Vehicule> findByStatut(String statut, Pageable pageable);

    Page<Vehicule> findByCapaciteGreaterThan(int capacite, Pageable pageable);
}