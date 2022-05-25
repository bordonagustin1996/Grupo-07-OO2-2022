package com.unla.Grupo07OO22022.models;


import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor

public class SpaceModel {

	
	private int id;
	private LocalDate date;
	private String turn;
	
	
	public SpaceModel(int id, LocalDate date, String turn) {
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



	public String getTurn() {
		return turn;
	}



	public void setTurn(String turn) {
		this.turn = turn;
	}




	

	
	
}
