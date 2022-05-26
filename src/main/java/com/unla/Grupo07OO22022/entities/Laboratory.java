package com.unla.Grupo07OO22022.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "laboratory")
public class Laboratory extends Classroom {
	
	@Column(name = "number_pc")
	private int numberPC;
	
	@Column(name = "number_chairs")
	private int numberChairs;

	public Laboratory() {}

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
