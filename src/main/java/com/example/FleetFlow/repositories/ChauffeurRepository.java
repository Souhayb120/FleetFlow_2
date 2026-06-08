package com.example.FleetFlow.repositories;

import com.example.FleetFlow.DTO.ChauffeurDTO;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.models.Livraison;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;



public interface ChauffeurRepository extends JpaRepository<Chauffeur, Integer> {

    Page<Chauffeur> findByIsDisponibleTrue(Pageable pageable);

    Page<Chauffeur> findByPermisTypeAndIsDisponible(
            String permisType,
            Boolean isDisponible,
            Pageable pageable
    );

}
