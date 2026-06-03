package com.example.FleetFlow.services.Impl;

import com.example.FleetFlow.DTO.ChauffeurDTO;
import com.example.FleetFlow.DTO.CreateChauffeurDTO;
import com.example.FleetFlow.Mapper.ChauffeurMapper;
import com.example.FleetFlow.models.Chauffeur;
import com.example.FleetFlow.repositories.ChauffeurRepository;
import com.example.FleetFlow.services.ChauffeurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChauffeurServiceImpl implements ChauffeurService {

    private final ChauffeurMapper mapper;
    private final ChauffeurRepository chauffeurRepository;

    public ChauffeurServiceImpl(
            ChauffeurMapper mapper,
            ChauffeurRepository chauffeurRepository
    ) {
        this.mapper = mapper;
        this.chauffeurRepository = chauffeurRepository;
    }

    @Override
    public void ajouterChauffeur(CreateChauffeurDTO chauffeur) {
        chauffeurRepository.save(mapper.toEntity(chauffeur));
    }

    @Override
    public void deleteChauffeur(int id) {
        chauffeurRepository.deleteById(id);
    }



    @Override
    public Page<ChauffeurDTO> displayChauffeurs(Pageable pageable) {
        return chauffeurRepository.findAll(pageable)
                .map(chauffeur -> {
                    ChauffeurDTO dto = mapper.toDTO(chauffeur);
                    dto.setNombreVehicules(chauffeur.getVichelList().size());
                    dto.setNombreLivraisons(chauffeur.getLivraisonList().size());
                    return dto;
                });
    }

    @Override
    public Chauffeur updateChauffeur(int id, Chauffeur newData) {

        Chauffeur chauffeur = chauffeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chauffeur not found"));

        chauffeur.setNom(newData.getNom());
        chauffeur.setPhone(newData.getPhone());
        chauffeur.setIsDisponible(newData.getIsDisponible());
        chauffeur.setPermisType(newData.getPermisType());

        return chauffeurRepository.save(chauffeur);
    }


    @Override
    public Page<ChauffeurDTO> findByDisponibility(Pageable pageable) {

        return chauffeurRepository.findByIsDisponibleTrue(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Page<ChauffeurDTO> findByPermisTypeDisponible(
            String permisType,
            Boolean isDisponible,
            Pageable pageable
    ) {

        return chauffeurRepository
                .findByPermisTypeAndIsDisponible(
                        permisType,
                        isDisponible,
                        pageable
                )
                .map(mapper::toDTO);
    }

    @Override
    public Page<String> displayChauffeursByNom(Pageable pageable) {

        return chauffeurRepository.findAll(pageable)
                .map(Chauffeur::getNom);
    }
}