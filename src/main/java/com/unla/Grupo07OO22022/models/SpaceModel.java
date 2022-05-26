package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class SpaceModel {
	
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private char turn;
	
	private boolean free;
		
	public SpaceModel() {}

	public SpaceModel(int id, LocalDate date, char turn) {
		this.setId(id);
		this.date = date;
		this.turn = turn;
	}	
	
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

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
}
