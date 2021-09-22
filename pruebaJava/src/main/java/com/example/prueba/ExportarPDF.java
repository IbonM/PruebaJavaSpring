package com.example.prueba;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 
 
public class ExportarPDF {
    private List<Coche> listCoches;
    private String marca;
     
    public ExportarPDF(List<Coche> listCoches, String marca) {
        this.listCoches = listCoches;
        this.marca = marca;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(16);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Marca", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Modelo", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Matricula", font));
        table.addCell(cell);
     
    }
     
    private void writeTableData(PdfPTable table) {
        for (Coche coche : listCoches) {
        	if(coche.getMarca().equalsIgnoreCase(marca)||marca=="")
        	{
        		table.addCell(String.valueOf(coche.getId()));
        		table.addCell(coche.getMarca());
        		table.addCell(coche.getModelo());
        		table.addCell(coche.getMatricula().toString());
        	}
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.RED);
         
        Paragraph p = new Paragraph("Lista de Coches", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}