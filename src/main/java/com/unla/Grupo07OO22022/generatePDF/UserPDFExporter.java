package com.unla.Grupo07OO22022.generatePDF;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.unla.Grupo07OO22022.entities.User;

public class UserPDFExporter {

	private List<User> users;

	public UserPDFExporter() {};
	
	public UserPDFExporter(List<User> users) {	
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(7);         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(10);
        font.setColor(Color.BLACK);      
        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Apellido", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tipo de documento", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Número de documento", font));
        table.addCell(cell);         
        cell.setPhrase(new Phrase("Correo electrónico", font));
        table.addCell(cell);         
        cell.setPhrase(new Phrase("Usuario", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Rol", font));
        table.addCell(cell);
    }
	
	private void writeTableData(PdfPTable table) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(10);
        for (User user : users) {         	
        	table.addCell(new PdfPCell(new Phrase(user.getName(), font)));
        	table.addCell(new PdfPCell(new Phrase(user.getSurname(), font)));
        	table.addCell(new PdfPCell(new Phrase(user.getDocumentType(), font)));
        	table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getDocumentNumber()), font)));
        	table.addCell(new PdfPCell(new Phrase(user.getEmail(), font)));
        	table.addCell(new PdfPCell(new Phrase(user.getUsername(), font)));
        	table.addCell(new PdfPCell(new Phrase(user.getUserRole().getName(), font)));    
    	}
    }
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		 Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());	         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        font.setColor(Color.BLACK);         
        Paragraph p = new Paragraph("Lista de usuarios", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);         
        document.add(p);         
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);           
        table.setSpacingBefore(10);         
        writeTableHeader(table);
        writeTableData(table);         
        document.add(table);         
        document.close();       
    }
}
