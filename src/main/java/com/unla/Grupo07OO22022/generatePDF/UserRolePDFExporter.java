package com.unla.Grupo07OO22022.generatePDF;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unla.Grupo07OO22022.entities.UserRole;

public class UserRolePDFExporter {
	
	private List<UserRole> userRoles;

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRolePDFExporter(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
		
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(1);         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(10);
        font.setColor(Color.BLACK);         
        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);
    }
	
	private void writeTableData(PdfPTable table) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(10);
        for (UserRole userRole : userRoles) {            
        	table.addCell(new PdfPCell(new Phrase(userRole.getName(), font)));         
    	}
    }
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		 Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());	         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        font.setColor(Color.BLACK);         
        Paragraph p = new Paragraph("Lista de roles", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);         
        document.add(p);         
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(50f);        
        table.setSpacingBefore(10);         
        writeTableHeader(table);
        writeTableData(table);         
        document.add(table);         
        document.close();       
    }
}
