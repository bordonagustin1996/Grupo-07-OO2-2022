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
import com.unla.Grupo07OO22022.entities.Matter;

@Component("matter/index")
public class MatterPDF extends AbstractPdfView{
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		@SuppressWarnings("unchecked")
		List<Matter> listMatter = (List<Matter>) model.get("matters");
		PdfPTable tablaMatte= new PdfPTable(2);
		
		listMatter.forEach(career ->{
			tablaMatte.addCell(career.getName());
			tablaMatte.addCell(career.getCareer().getName());
		});
		
		document.add(tablaMatte);
		
	}

}
