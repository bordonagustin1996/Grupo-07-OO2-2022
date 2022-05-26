package com.unla.Grupo07OO22022.models;

public class TraditionalModel extends ClassroomModel {
	
	private int numberBenches;
	
	private String chalkboard;
	
	private boolean hasProjector;

	public TraditionalModel() {}

	public int getNumberBenches() {
		return numberBenches;
	}

	public void setNumberBenches(int numberBenches) {
		this.numberBenches = numberBenches;
	}

	public String getChalkboard() {
		return chalkboard;
	}

	public void setChalkboard(String chalkboard) {
		this.chalkboard = chalkboard;
	}

	public boolean isHasProjector() {
		return hasProjector;
	}

	public void setHasProjector(boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
	
}
