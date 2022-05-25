package com.unla.Grupo07OO22022.models;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ClassroomModel {
	
	private int id;
	
	private int number;
	
	
	
	public ClassroomModel() {}

	public ClassroomModel(int id, int number) {
		
		this.setId(id);
		this.number = number;
		
	}

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

	
}
