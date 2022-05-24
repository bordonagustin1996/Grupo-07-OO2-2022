package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

public class FinalModel extends OrderNoteModel{

	protected int idFinal;
	
	protected LocalDate examDate;

	public FinalModel() {
		super();
	}

	public FinalModel(int idFinal, LocalDate examDate) {
		super();
		setIdFinal(idFinal);
		this.examDate = examDate;
	}

	public int getIdFinal() {
		return idFinal;
	}

	public void setIdFinal(int idFinal) {
		this.idFinal = idFinal;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}
	
	
}
