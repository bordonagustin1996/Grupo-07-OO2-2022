package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.unla.Grupo07OO22022.entities.Classroom;

public class SpaceModel {
	
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private char turn;
	
	private Classroom classroom;
	
	private boolean free;
		
	public SpaceModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
}
