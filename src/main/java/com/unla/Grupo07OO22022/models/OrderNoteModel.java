package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

public class OrderNoteModel {
	
	protected int idOrderNote;
	
	protected LocalDate date;
	
	protected String turn;
	
	protected int numberStudent;
	
	protected String observations;

	public OrderNoteModel() {
		super();
	}

	public OrderNoteModel(int idOrderNote, LocalDate date, String turn, int numberStudent, String observations) {
		super();
		setIdOrderNote(idOrderNote);
		this.date = date;
		this.turn = turn;
		this.numberStudent = numberStudent;
		this.observations = observations;
	}

	public int getIdOrderNote() {
		return idOrderNote;
	}

	public void setIdOrderNote(int idOrderNote) {
		this.idOrderNote = idOrderNote;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public int getNumberStudent() {
		return numberStudent;
	}

	public void setNumberStudent(int numberStudent) {
		this.numberStudent = numberStudent;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	

}
