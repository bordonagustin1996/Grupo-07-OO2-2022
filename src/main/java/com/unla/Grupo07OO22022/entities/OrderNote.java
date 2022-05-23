package com.unla.Grupo07OO22022.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.boot.model.source.spi.InheritanceType;

import lombok.NoArgsConstructor;

@Entity

@SQLDelete(sql = "UPDATE OrderNote SET enabled = false WHERE id=?")
@Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
public class OrderNote{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name="turn")
	private String turn;
	
	@Column(name="numberStudent")
	private int numberStudent;
	
	@Column(name="observation")
	private String observation;
	
	@Column(name = "enabled")
	private boolean enabled = true;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="matter_id")
	private Matter matter;

	public OrderNote() {
		super();
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

	public int getNumberStudent() {
		return numberStudent;
	}

	public void setNumberStudent(int numberStudent) {
		this.numberStudent = numberStudent;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
