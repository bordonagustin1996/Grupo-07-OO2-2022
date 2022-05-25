package com.unla.Grupo07OO22022.entities;

import java.time.LocalDate;
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


public class Space {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name ="date")
	private LocalDate date;
	
	@Column (name ="turn")
	private String turn;
	
	

	public Space(int id, LocalDate date, String turn) {
		this.id = id;
		this.date = date;
		this.turn = turn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	
	
	
	
	
	
	
	
	

}
