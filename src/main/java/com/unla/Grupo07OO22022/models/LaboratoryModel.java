package com.unla.Grupo07OO22022.models;

import lombok.NoArgsConstructor;


@NoArgsConstructor

public class LaboratoryModel {
	
	
	private int id;
	private int number_benches;
	private int number_pc;


public LaboratoryModel(int id, int number_benches, int number_pc) {
	this.id = id;
	this.number_benches = number_benches;
	this.number_pc = number_pc;

}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public int getNumber_benches() {
	return number_benches;
}


public void setNumber_benches(int number_benches) {
	this.number_benches = number_benches;
}


public int getNumber_pc() {
	return number_pc;
}


public void setNumber_pc(int number_pc) {
	this.number_pc = number_pc;
}



	
	
	
	
	
	
	
	
}