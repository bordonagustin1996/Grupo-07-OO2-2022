package com.unla.Grupo07OO22022.models;

public class CareerModel {
	
	private int idCareer;
	
	private String name;

	public CareerModel() {
		super();
	}

	public CareerModel(int idCareer, String name) {
		super();
		setIdCareer(idCareer);
		this.name = name;
	}

	public int getIdCareer() {
		return idCareer;
	}

	public void setIdCareer(int idCareer) {
		this.idCareer = idCareer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
