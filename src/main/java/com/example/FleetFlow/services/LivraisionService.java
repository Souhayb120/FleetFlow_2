package com.example.FleetFlow.services;

import com.example.FleetFlow.models.Livraison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface LivraisionService {

    Livraison creeLivraision(Livraison l);

    Livraison assigner(Long livraisonId, Integer chauffeurId, Long vehiculeId);

    Livraison updateStatut(Long id, String statut);

    Page<Livraison> getAll(Pageable pageable);

    Page<Livraison> getbystatut(String statut, Pageable pageable);

    Page<Livraison> findbyclientId(Long id, Pageable pageable);

    Page<Livraison> findbetweendates(
            LocalDate date1,
            LocalDate date2,
            Pageable pageable
    );

    Page<Livraison> findbyadressedestination(
            String ville,
            Pageable pageable
    );

    Page<Livraison> getLivraisonByChaffeurDisponible(
            Pageable pageable
    );
}