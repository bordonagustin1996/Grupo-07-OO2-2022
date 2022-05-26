package com.unla.Grupo07OO22022.models;

import com.unla.Grupo07OO22022.entities.Building;

public class ClassroomModel {
	
	private int id;
	
	private int number;
	
	private Building building;
	
	public ClassroomModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
	
}
