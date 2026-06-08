package com.example.FleetFlow.repositories;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.models.Livraison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {

    Page<Livraison> findByStatut(
            String statut,
            Pageable pageable
    );

    Page<Livraison> findByClientId(
            Long clientId,
            Pageable pageable
    );

    @Query("""
           SELECT l
           FROM Livraison l
           WHERE l.dateLivraison
           BETWEEN :date1 AND :date2
           """)
    Page<Livraison> findBetweenDates(
            LocalDate date1,
            LocalDate date2,
            Pageable pageable
    );

    Page<Livraison> findByAdresseDestination(
            String ville,
            Pageable pageable
    );

    Page<Livraison> findByChauffeurIsDisponibleTrue(
            Pageable pageable
    );

    Page<Livraison> findByChauffeur_Id(long chauffeurId, Pageable pageable);

    Livraison findLivraisonsById(Long id);

}