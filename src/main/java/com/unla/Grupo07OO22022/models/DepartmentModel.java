package com.unla.Grupo07OO22022.models;

public class DepartmentModel {

	private int idDepartment;
	
	private String name;

	public DepartmentModel() {
		super();
	}

	public DepartmentModel(int idDepartment, String name) {
		super();
		setIdDepartment(idDepartment);
		this.name = name;
	}

	public int getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
