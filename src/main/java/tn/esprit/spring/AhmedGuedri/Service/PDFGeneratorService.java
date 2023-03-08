package tn.esprit.spring.AhmedGuedri.Service;



import com.itextpdf.text.pdf.draw.LineSeparator;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repository.OrderRepo;
import tn.esprit.spring.AhmedGuedri.entities.Orders;

import java.awt.*;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class PDFGeneratorService {

  @Autowired
   OrderRepo orderRepo;
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,18);
        fontTitle.setSize(18);
        Paragraph titlefacture = new Paragraph("FACTURE", fontTitle);
        titlefacture.setAlignment(Paragraph.ALIGN_LEFT);
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Orders o1 = orderRepo.findById(1L).get();
        Paragraph paragraph = new Paragraph(o1.toString(), fontParagraph);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);

        // Add an image to the document
        String imageUrl = "https://i.ibb.co/ZL0QKXZ/LogoRata.png"; // Replace with the URL of your image
        Image img = Image.getInstance(new URL(imageUrl));
        img.scaleToFit(50, 50); // Scale the image to fit the page
        img.setAlignment(Element.ALIGN_RIGHT);

        // Create a LineSeparator object
        Paragraph paragraph2 = new Paragraph("Merci pour votre commande", fontParagraph);

        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph3 = new Paragraph("Bonjou,pour information - nous avons recu votre commande", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph line = new Paragraph("___________________________________________________________________________", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);


// Add the line to the document

        document.add(img);

        document.add(titlefacture);
        document.add(line);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph);

        document.close();
    }

}
