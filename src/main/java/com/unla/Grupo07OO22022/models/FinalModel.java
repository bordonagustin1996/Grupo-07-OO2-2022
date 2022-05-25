package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class FinalModel extends OrderNoteModel {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate examDate;
	
	public FinalModel() {}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}	
  
}
