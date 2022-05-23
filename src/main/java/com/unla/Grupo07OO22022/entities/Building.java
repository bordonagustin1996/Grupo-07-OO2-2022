package com.unla.Grupo07OO22022.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor

@SQLDelete(sql = "UPDATE user SET enabled = false WHERE id=?")

public class Building {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "building")
	private String building;

	public Building(int id, String building) {
		this.id = id;
		this.building = building;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}
	
	
	
	
	
	
	
	

}
