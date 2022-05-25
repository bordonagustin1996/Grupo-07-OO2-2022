package com.unla.Grupo07OO22022.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor

@SQLDelete(sql = "UPDATE user SET enabled = false WHERE id=?")

public class Traditional {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "number_chairs")
	private int number_chairs;
	
	@Column (name = "chalkboard")
	private String chalkboard;
	
	@Column (name = "has_proyector")
	private boolean has_proyector;

	public Traditional(int id, int number_chairs, String chalkboard, boolean has_proyector) {
		this.id = id;
		this.number_chairs = number_chairs;
		this.chalkboard = chalkboard;
		this.has_proyector = has_proyector;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber_chairs() {
		return number_chairs;
	}

	public void setNumber_chairs(int number_chairs) {
		this.number_chairs = number_chairs;
	}

	public String getChalkboard() {
		return chalkboard;
	}

	public void setChalkboard(String chalkboard) {
		this.chalkboard = chalkboard;
	}

	public boolean isHas_proyector() {
		return has_proyector;
	}

	public void setHas_proyector(boolean has_proyector) {
		this.has_proyector = has_proyector;
	}
	
	
	

}
