package com.example.FleetFlow.controllers;

import com.example.FleetFlow.DTO.LivraisionDTO;
import com.example.FleetFlow.Mapper.LivraisionMapper;
import com.example.FleetFlow.models.Livraison;
import com.example.FleetFlow.services.LivraisionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/livraison")
public class LivraisonsController {

    @Autowired
    private LivraisionService livraisionServices;

    @Autowired
    private LivraisionMapper livraisionMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public LivraisionDTO creatLivraision(@RequestBody @Valid LivraisionDTO dto) {

        Livraison livraison = livraisionMapper.toEntity(dto);
        Livraison saved = livraisionServices.creeLivraision(livraison);

        return livraisionMapper.toDTO(saved);
    }

    @PutMapping("/{id}/assign")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public LivraisionDTO assign(
            @PathVariable long id,
            @RequestParam long chauffeurId,
            @RequestParam long vehiculeId) {

        Livraison livraison = livraisionServices.assigner(
                id,
                (int) chauffeurId,
                vehiculeId
        );

        return livraisionMapper.toDTO(livraison);
    }

    @PutMapping("/{id}/statut")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public LivraisionDTO updateStatut(
            @PathVariable Long id,
            @RequestParam String statut) {

        Livraison livraison = livraisionServices.updateStatut(id, statut);

        return livraisionMapper.toDTO(livraison);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Page<LivraisionDTO> list(
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return livraisionServices.getAll(pageable)
                .map(livraisionMapper::toDTO);
    }

    @GetMapping("/getLivraisonByChauffeurDisponible")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Page<LivraisionDTO> getlivraisonByChauffeurDis(
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return livraisionServices
                .getLivraisonByChaffeurDisponible(pageable)
                .map(livraisionMapper::toDTO);
    }

    @GetMapping("/statut")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Page<LivraisionDTO> getbystatut(
            @RequestParam String statut,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return livraisionServices
                .getbystatut(statut, pageable)
                .map(livraisionMapper::toDTO);
    }

    @GetMapping("/client")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Page<LivraisionDTO> findbyclientid(
            @RequestParam Long id,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return livraisionServices
                .findbyclientId(id, pageable)
                .map(livraisionMapper::toDTO);
    }

    @GetMapping("/dates")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Page<LivraisionDTO> findbetweendates(
            @RequestParam LocalDate date1,
            @RequestParam LocalDate date2,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return livraisionServices
                .findbetweendates(date1, date2, pageable)
                .map(livraisionMapper::toDTO);
    }

    @GetMapping("/destination")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Page<LivraisionDTO> findbydestinationadress(
            @RequestParam String ville,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return livraisionServices
                .findbyadressedestination(ville, pageable)
                .map(livraisionMapper::toDTO);
    }


    @GetMapping("/meLivraision")
    @PreAuthorize("hasAnyRole('ADMIN','CHAUFFEUR')")
    public Page<LivraisionDTO> findlivraisonByChaffeur(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam int id) {
        Page<LivraisionDTO> dtos = livraisionServices.findLivraisonByChaffeur(id,page,size);
        return  dtos;
    }


    @PatchMapping("/editLivraisonStatutByChaffeur")
    public void editLivraisonStatut(
            @RequestParam long id,
            @RequestParam String statut
    ){
        livraisionServices.editLivraisonStatutByChauffeur(id,statut);
    }


}