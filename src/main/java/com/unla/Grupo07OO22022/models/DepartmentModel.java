package com.unla.Grupo07OO22022.models;

public class DepartmentModel {

	private int id;
	
	private String name;

	public DepartmentModel() {
		super();
	}

	public DepartmentModel(int id, String name) {
		super();
		setId(id);
		this.name = name;
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
