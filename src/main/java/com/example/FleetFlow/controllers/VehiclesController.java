package com.example.FleetFlow.controllers;

import com.example.FleetFlow.DTO.VehiculeDTO;
import com.example.FleetFlow.Mapper.VehiculeMapper;
import com.example.FleetFlow.models.Vehicule;
import com.example.FleetFlow.services.Impl.VehiculeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicules")
public class VehiclesController {

    @Autowired
    private VehiculeServiceImpl vehculeService;

    @Autowired
    private VehiculeMapper vehiculeMapper;

    @PostMapping
    public Vehicule ajouterVehucle(@RequestBody @Valid VehiculeDTO v){
        return vehculeService.ajouterVehicule(v);
    }

    @PutMapping("/{id}")
    public VehiculeDTO modifierVehicule(
            @PathVariable Long id,
            @RequestBody VehiculeDTO dto
    ) {
        Vehicule vehicule = vehiculeMapper.toEntity(dto);
        Vehicule updated = vehculeService.modifierVehicule(id, vehicule);

        return vehiculeMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable long id){
        vehculeService.supprimerVehicule(id);
    }

    @GetMapping("/disponiblesVehicules")
    public Page<VehiculeDTO> vehicules(
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return vehculeService.listerVehicule(pageable);
    }

    @GetMapping("/statut")
    public Page<Vehicule> findbystatut(
            @RequestParam String statut,
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return vehculeService.findByStatut(
                statut,
                pageable
        );
    }

    @GetMapping("/capacity")
    public Page<Vehicule> findGreaterCapacitythan(
            @RequestParam int capacity,
            @RequestParam int page,
            @RequestParam int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return vehculeService.findGreaterCapacityThan(
                capacity,
                pageable
        );
    }
}