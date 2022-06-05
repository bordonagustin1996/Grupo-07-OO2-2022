package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.Min;

public class LaboratoryModel extends ClassroomModel {
	
	@Min(1)
	private int numberPC;
	
	@Min(1)
	private int numberChairs;

	public LaboratoryModel() {}

	public int getNumberPC() {
		return numberPC;
	}

	public void setNumberPC(int numberPC) {
		this.numberPC = numberPC;
	}

	public int getNumberChairs() {
		return numberChairs;
	}

	public void setNumberChairs(int numberChairs) {
		this.numberChairs = numberChairs;
	}
	
}
