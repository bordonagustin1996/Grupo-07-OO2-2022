package com.unla.Grupo07OO22022.models;

import com.unla.Grupo07OO22022.entities.Department;

public class CareerModel {

	private int id;

	private String name;
	
	private Department department;

	public CareerModel() {
		super();
	}

	public CareerModel(int id, String name) {
		super();
		setId(id);
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

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
