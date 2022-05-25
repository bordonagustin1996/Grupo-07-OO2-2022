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

public class Laboratory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "number_benches")
	private int number_benches;
	
	@Column (name = "number_PC")
	private int number_PC;

	public Laboratory(int id, int number_benches, int number_PC) {
		this.id = id;
		this.number_benches = number_benches;
		this.number_PC = number_PC;
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

	public int getNumber_PC() {
		return number_PC;
	}

	public void setNumber_PC(int number_PC) {
		this.number_PC = number_PC;
	}
	
	
	
	

}
