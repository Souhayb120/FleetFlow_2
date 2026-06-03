    package com.example.FleetFlow.services;

    import com.example.FleetFlow.models.Chauffeur;
    import com.example.FleetFlow.models.Livraison;
    import com.example.FleetFlow.models.Vehicule;
    import com.example.FleetFlow.repositories.ChauffeurRepository;
    import com.example.FleetFlow.repositories.LivraisonRepository;
    import com.example.FleetFlow.repositories.VehculeRepository;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;

    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertFalse;
    import static org.mockito.Mockito.when;
    @ExtendWith(MockitoExtension.class)

    public class LivraisionServiceImplTest {
        @Mock
        private LivraisonRepository livraisonRepository;
        @Mock
        private ChauffeurRepository chauffeurRepository;
        @Mock
        private VehculeRepository vehculeRepository;

        @InjectMocks
        private LivraisionService livraisionService;


        @Test
        void shouldCreateLivraisonWithInitialStatus(){

            Livraison livraison = new Livraison();

            livraisionService.creeLivraision(livraison);

            assertEquals("EN_ATTENTE",livraison.getStatut());;
        }
        @Test
        void shouldAAssignerChauffeurVehiculeAuneLivraison(){
            //GIVEN
            long livraisionID= 3;
            int chauffeurID=2;
            long vehiculeID= 3;
            Livraison livraison = new Livraison();
            Chauffeur chauffeur =new Chauffeur();
            Vehicule vehicule = new Vehicule();

            when(livraisonRepository.findById(livraisionID)).thenReturn(Optional.of(livraison));
            when(chauffeurRepository.findById(chauffeurID)).thenReturn(Optional.of(chauffeur));
            when(vehculeRepository.findById(vehiculeID)).thenReturn(Optional.of(vehicule));
            when(livraisonRepository.save(livraison)).thenReturn(livraison);
            //WHEN
            Livraison rs = livraisionService.assigner(livraisionID,chauffeurID,vehiculeID);

            //THEN
            assertEquals(chauffeur,rs.getChauffeur());
            assertEquals(vehicule,rs.getVehicule());

            assertEquals("ENCOURS",rs.getStatut());
            assertEquals("Occuppier",vehicule.getStatut());

            assertFalse(chauffeur.getIsDisponible());
        }

        @Test
        void shouldUpdateLivraisonStatus() {

            // GIVEN
            Long id = 1L;
            Livraison livraison = new Livraison();
            livraison.setStatut("EN_ATTENTE");

            String newStatut = "ENCOURS";

            when(livraisonRepository.findById(id))
                    .thenReturn(Optional.of(livraison));

            when(livraisonRepository.save(livraison))
                    .thenReturn(livraison);

            // WHEN
            Livraison rs = livraisionService.updateStatut(id, newStatut);

            // THEN
            assertEquals(newStatut, rs.getStatut());
        }
    }
