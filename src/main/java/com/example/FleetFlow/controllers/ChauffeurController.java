package com.example.FleetFlow.controllers;

import com.example.FleetFlow.DTO.ChauffeurDTO;
import com.example.FleetFlow.DTO.CreateChauffeurDTO;
import com.example.FleetFlow.DTO.LivraisionDTO;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.services.ChauffeurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chauffeurs")
public class ChauffeurController {

    @Autowired
    private ChauffeurService chauffeurService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void saveChauffeur(@Valid @RequestBody CreateChauffeurDTO chauffeur){
        chauffeurService.ajouterChauffeur(chauffeur);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Page<ChauffeurDTO> displayChauffeurs(
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurService.displayChauffeurs(pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteChauffeur(@PathVariable int id){
        chauffeurService.deleteChauffeur(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Chauffeur updateChauffeur(
            @PathVariable int id,
            @RequestBody Chauffeur chauffeur
    ){
        return chauffeurService.updateChauffeur(id, chauffeur);
    }

    @GetMapping("/chaffeursDisponible")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ChauffeurDTO> findByIsDisponible(
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurService.findByDisponibility(pageable);
    }

    @GetMapping("/{permisType}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ChauffeurDTO> displayChauffeurs(
            @PathVariable String permisType,
            @RequestParam Boolean isDisponible,
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return chauffeurService.findByPermisTypeDisponible(
                permisType,
                isDisponible,
                pageable
        );
    }

    @GetMapping("/displayChauffeurByNom")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<String> displayChauffeursByNom(
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurService.displayChauffeursByNom(pageable);
    }





}