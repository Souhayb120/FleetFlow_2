package com.example.FleetFlow.services.Impl;


import com.example.FleetFlow.DTO.VehiculeDTO;
import com.example.FleetFlow.Mapper.VehiculeMapper;
import com.example.FleetFlow.models.Vehicule;
import com.example.FleetFlow.repositories.VehculeRepository;
import com.example.FleetFlow.services.VehiculeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    private final VehculeRepository vehculeRepository;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeServiceImpl(
            VehculeRepository vehculeRepository,
            VehiculeMapper vehiculeMapper
    ) {
        this.vehculeRepository = vehculeRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    @Override
    public Vehicule ajouterVehicule(VehiculeDTO vehiculeDTO) {

        return vehculeRepository.save(
                vehiculeMapper.toEntity(vehiculeDTO)
        );
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehicule) {

        Vehicule existingVehicule = vehculeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicule not found"));

        existingVehicule.setMatricule(vehicule.getMatricule());
        existingVehicule.setType(vehicule.getType());
        existingVehicule.setCapacite(vehicule.getCapacite());
        existingVehicule.setStatut(vehicule.getStatut());

        return vehculeRepository.save(existingVehicule);
    }

    @Override
    public void supprimerVehicule(Long id) {

        if (!vehculeRepository.existsById(id)) {
            throw new RuntimeException("Vehicule not found");
        }

        vehculeRepository.deleteById(id);
    }

    @Override
    public Page<VehiculeDTO> listerVehicule(Pageable pageable) {

        return vehculeRepository.findAll(pageable)
                .map(vehiculeMapper::toDTO);
    }

    @Override
    public Page<Vehicule> findByStatut(
            String statut,
            Pageable pageable
    ) {
        return vehculeRepository.findByStatut(
                statut,
                pageable
        );
    }

    @Override
    public Page<Vehicule> findGreaterCapacityThan(
            int capacity,
            Pageable pageable
    ) {
        return vehculeRepository.findByCapaciteGreaterThan(
                capacity,
                pageable
        );
    }
}