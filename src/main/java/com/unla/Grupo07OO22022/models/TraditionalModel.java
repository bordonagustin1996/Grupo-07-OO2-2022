package com.unla.Grupo07OO22022.models;

import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor

public class TraditionalModel {
	
	private int id;
	private int number_chairs;
	private String chalkboard;
	private boolean has_proyector;
	
	
	public TraditionalModel(int id, int number_chairs, String chalkboard, boolean has_proyector) {
		this.id = id;
		this.number_chairs = number_chairs;
		this.chalkboard = chalkboard;
		this.has_proyector = has_proyector;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumber_chairs() {
		return number_chairs;
	}


	public void setNumber_chairs(int number_chairs) {
		this.number_chairs = number_chairs;
	}


	public String getChalkboard() {
		return chalkboard;
	}


	public void setChalkboard(String chalkboard) {
		this.chalkboard = chalkboard;
	}


	public boolean isHas_proyector() {
		return has_proyector;
	}


	public void setHas_proyector(boolean has_proyector) {
		this.has_proyector = has_proyector;
	}
	
	

}
