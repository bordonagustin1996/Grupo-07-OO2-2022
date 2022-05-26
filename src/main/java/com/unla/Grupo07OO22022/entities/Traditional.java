package com.unla.Grupo07OO22022.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "traditional")
public class Traditional extends Classroom {
	
	@Column(name = "number_benches")
	private int numberBenches;
	
	@Column(name = "chalkboard")
	private String chalkboard;
	
	@Column(name = "has_projector")
	private boolean hasProjector;

	public Traditional() {}

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
