package com.example.FleetFlow.services.Impl;

import com.example.FleetFlow.models.Livraison;
import com.example.FleetFlow.repositories.LivraisonRepository;
import com.example.FleetFlow.services.ReportService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ReportServiceImpl implements ReportService {

    private final LivraisonRepository livraisonRepository;

    public ReportServiceImpl(LivraisonRepository livraisonRepository) {
        this.livraisonRepository = livraisonRepository;
    }

    @Override
    public ByteArrayInputStream generateDeliveriesReport() {
        List<Livraison> livraisons = livraisonRepository.findAll();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);

            Paragraph para = new Paragraph("Rapport de Livraisons", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            Stream.of("ID", "Date", "Départ", "Destination", "Statut")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });

            for (Livraison livraison : livraisons) {
                table.addCell(String.valueOf(livraison.getId()));
                table.addCell(livraison.getDateLivraison() != null ? livraison.getDateLivraison().toString() : "");
                table.addCell(livraison.getAdresseDepart());
                table.addCell(livraison.getAdresseDestination());
                table.addCell(livraison.getStatut());
            }

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            throw new RuntimeException("Error generating PDF", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
