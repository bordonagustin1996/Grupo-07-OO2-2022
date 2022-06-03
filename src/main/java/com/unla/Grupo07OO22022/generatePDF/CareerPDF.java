package com.unla.Grupo07OO22022.generatePDF;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.unla.Grupo07OO22022.entities.Career;


@Component("career/index")
public class CareerPDF extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		@SuppressWarnings("unchecked")
		List<Career> listCareer = (List<Career>) model.get("careers");
		PdfPTable tablaCareer= new PdfPTable(2);
		
		listCareer.forEach(career ->{
			tablaCareer.addCell(career.getName());
			tablaCareer.addCell(career.getName());
		});
		
		document.add(tablaCareer);
		
	}

}
