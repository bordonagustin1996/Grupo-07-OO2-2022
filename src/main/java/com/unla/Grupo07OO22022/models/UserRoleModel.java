package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.NotBlank;

public class UserRoleModel {

	private int id;
	
	@NotBlank(message = "El nombre del rol es requerido")
	private String name;

	public UserRoleModel() {}

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
