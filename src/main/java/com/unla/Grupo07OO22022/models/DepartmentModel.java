package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.NotBlank;

public class DepartmentModel {

	private int id;
	
	@NotBlank(message = "El nombre es requerido")
	private String name;

	public DepartmentModel() {}
	
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
