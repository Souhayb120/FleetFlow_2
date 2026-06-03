package com.example.FleetFlow.controllers;

import com.example.FleetFlow.DTO.ChauffeurDTO;
import com.example.FleetFlow.DTO.CreateChauffeurDTO;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.services.ChauffeurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chauffeurs")
public class ChauffeurController {

    @Autowired
    private ChauffeurService chauffeurService;

    @PostMapping
    public void saveChauffeur(@Valid @RequestBody CreateChauffeurDTO chauffeur){
        chauffeurService.ajouterChauffeur(chauffeur);
    }

    @GetMapping
    public Page<ChauffeurDTO> displayChauffeurs(
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurService.displayChauffeurs(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteChauffeur(@PathVariable int id){
        chauffeurService.deleteChauffeur(id);
    }

    @PutMapping("/{id}")
    public Chauffeur updateChauffeur(
            @PathVariable int id,
            @RequestBody Chauffeur chauffeur
    ){
        return chauffeurService.updateChauffeur(id, chauffeur);
    }

    @GetMapping("/chaffeursDisponible")
    public Page<ChauffeurDTO> findByIsDisponible(
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurService.findByDisponibility(pageable);
    }

    @GetMapping("/{permisType}")
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
    public Page<String> displayChauffeursByNom(
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return chauffeurService.displayChauffeursByNom(pageable);
    }
}