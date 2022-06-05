package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.NotBlank;

public class BuildingModel {

	private int id;
	
	@NotBlank
	private String name;
	
	public BuildingModel () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
