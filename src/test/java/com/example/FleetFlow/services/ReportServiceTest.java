package com.example.FleetFlow.services;

import com.example.FleetFlow.models.Livraison;
import com.example.FleetFlow.repositories.LivraisonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Test
    void shouldGeneratePdfReport() {
        // GIVEN
        Livraison livraison = new Livraison();
        livraison.setAdresseDepart("Depart A");
        livraison.setAdresseDestination("Destination B");
        livraison.setStatut("EN_ATTENTE");
        livraison.setDateLivraison(LocalDate.now());
        livraisonRepository.save(livraison);

        // WHEN
        ByteArrayInputStream bis = reportService.generateDeliveriesReport();

        // THEN
        assertNotNull(bis);
        assertTrue(bis.available() > 0);
    }
}
