package com.example.FleetFlow.services.Impl;

import com.example.FleetFlow.DTO.LivraisionDTO;
import com.example.FleetFlow.Mapper.LivraisionMapper;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.models.Livraison;
import com.example.FleetFlow.models.Vehicule;
import com.example.FleetFlow.repositories.ChauffeurRepository;
import com.example.FleetFlow.repositories.LivraisonRepository;
import com.example.FleetFlow.repositories.VehiculeRepository;
import com.example.FleetFlow.services.LivraisionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LivraisionServiceImpl implements LivraisionService {

    private final LivraisonRepository livraisonRepository;
    private final ChauffeurRepository chauffeurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final LivraisionMapper livraisionMapper;

    public LivraisionServiceImpl(
            LivraisonRepository livraisonRepository,
            ChauffeurRepository chauffeurRepository,
            VehiculeRepository vehiculeRepository, LivraisionMapper livraisionMapper
    ) {
        this.livraisonRepository = livraisonRepository;
        this.chauffeurRepository = chauffeurRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.livraisionMapper = livraisionMapper;
    }

    @Override
    public Livraison creeLivraision(Livraison l) {
        l.setStatut("EN_ATTENTE");
        return livraisonRepository.save(l);
    }

    @Override
    public Livraison assigner(
            Long livraisonId,
            Integer chauffeurId,
            Long vehiculeId
    ) {

        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(() -> new RuntimeException("Livraison not found"));

        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId)
                .orElseThrow(() -> new RuntimeException("Chauffeur not found"));

        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Vehicule not found"));

        chauffeur.setIsDisponible(false);
        vehicule.setStatut("Occuppier");
        livraison.setStatut("ENCOURS");

        livraison.setChauffeur(chauffeur);
        livraison.setVehicule(vehicule);

        return livraisonRepository.save(livraison);
    }

    @Override
    public Livraison updateStatut(Long id, String statut) {

        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livraison not found"));

        livraison.setStatut(statut);

        return livraisonRepository.save(livraison);
    }



    @Override
    public Page<Livraison> getAll(Pageable pageable) {
        return livraisonRepository.findAll(pageable);
    }

    @Override
    public Page<Livraison> getbystatut(
            String statut,
            Pageable pageable
    ) {
        return livraisonRepository.findByStatut(statut, pageable);
    }

    @Override
    public Page<Livraison> findbyclientId(
            Long id,
            Pageable pageable
    ) {
        return livraisonRepository.findByClientId(id, pageable);
    }

    @Override
    public Page<Livraison> findbetweendates(
            LocalDate date1,
            LocalDate date2,
            Pageable pageable
    ) {
        return livraisonRepository.findBetweenDates(
                date1,
                date2,
                pageable
        );
    }

    @Override
    public Page<Livraison> findbyadressedestination(
            String ville,
            Pageable pageable
    ) {
        return livraisonRepository.findByAdresseDestination(
                ville,
                pageable
        );
    }

    @Override
    public Page<Livraison> getLivraisonByChaffeurDisponible(
            Pageable pageable
    ) {
        return livraisonRepository
                .findByChauffeurIsDisponibleTrue(pageable);
    }



    @Override
    public Page<LivraisionDTO> findLivraisonByChaffeur(long id, int page, int size) {
        Pageable p = PageRequest.of(page, size);
        Page<LivraisionDTO> livraisionDTOS = livraisonRepository.findByChauffeur_Id(id,p).map((livraison -> {LivraisionDTO l = livraisionMapper.toDTO(livraison);
            return l;
        }));
        return livraisionDTOS;
    }

    @Override
    public void editLivraisonStatutByChauffeur(long id, String statut) {
        Livraison livraison = livraisonRepository.findLivraisonsById(id);
        livraison.setStatut(statut);
        livraisonRepository.save(livraison);
    }


}
