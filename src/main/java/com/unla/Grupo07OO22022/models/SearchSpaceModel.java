package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.unla.Grupo07OO22022.entities.Classroom;

public class SearchSpaceModel {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	private char turn;
	
	private Classroom classroom;

	public SearchSpaceModel() {}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public char getTurn() {
		return turn;
	}

	public void setTurn(char turn) {
		this.turn = turn;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
}
