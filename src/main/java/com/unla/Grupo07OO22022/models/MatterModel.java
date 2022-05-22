package com.unla.Grupo07OO22022.models;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MatterModel {
	
	private int id;
	
	private String name;

	public MatterModel() {}

	public String getName() {
		return name;
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
