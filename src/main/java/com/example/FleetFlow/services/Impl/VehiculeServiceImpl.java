package com.example.FleetFlow.services.Impl;


import com.example.FleetFlow.DTO.VehiculeDTO;
import com.example.FleetFlow.Mapper.VehiculeMapper;
import com.example.FleetFlow.models.Vehicule;
import com.example.FleetFlow.repositories.VehiculeRepository;
import com.example.FleetFlow.services.VehiculeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeServiceImpl(
            VehiculeRepository vehiculeRepository,
            VehiculeMapper vehiculeMapper
    ) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    @Override
    public Vehicule ajouterVehicule(VehiculeDTO vehiculeDTO) {

        return vehiculeRepository.save(
                vehiculeMapper.toEntity(vehiculeDTO)
        );
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehicule) {

        Vehicule existingVehicule = vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicule not found"));

        existingVehicule.setMatricule(vehicule.getMatricule());
        existingVehicule.setType(vehicule.getType());
        existingVehicule.setCapacite(vehicule.getCapacite());
        existingVehicule.setStatut(vehicule.getStatut());

        return vehiculeRepository.save(existingVehicule);
    }

    @Override
    public void supprimerVehicule(Long id) {

        if (!vehiculeRepository.existsById(id)) {
            throw new RuntimeException("Vehicule not found");
        }

        vehiculeRepository.deleteById(id);
    }

    @Override
    public Page<VehiculeDTO> listerVehicule(Pageable pageable) {

        return vehiculeRepository.findAll(pageable)
                .map(vehiculeMapper::toDTO);
    }

    @Override
    public Page<Vehicule> findByStatut(
            String statut,
            Pageable pageable
    ) {
        return vehiculeRepository.findByStatut(
                statut,
                pageable
        );
    }

    @Override
    public Page<Vehicule> findGreaterCapacityThan(
            int capacity,
            Pageable pageable
    ) {
        return vehiculeRepository.findByCapaciteGreaterThan(
                capacity,
                pageable
        );
    }
}