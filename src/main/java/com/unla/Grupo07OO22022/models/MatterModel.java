package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.NotBlank;

import com.unla.Grupo07OO22022.entities.Career;

public class MatterModel {
	
	private int id;
	
	@NotBlank 
	private String name;
	
	private Career career;

	public MatterModel() {}

	public String getName() {
		return name;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
  
}
