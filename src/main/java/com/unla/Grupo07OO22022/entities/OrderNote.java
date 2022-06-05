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
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "order_note")
@SQLDelete(sql = "UPDATE order_note SET enabled = false WHERE id = ?")
@Inheritance(strategy = InheritanceType.JOINED)
public class OrderNote {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="enabled")
	private boolean enabled = true;
	
	@Column(name="date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@Column(name="turn")
	private char turn;  
	
	@Column(name="num_students")
	private int numStudents;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="matter_id")
	private Matter matter;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="classroom_id")
	private Classroom classroom;
	
	@Column(name="confirmed")
	private boolean confirmed;
	
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@Column(name="observations", length=200)
	private String observations;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public OrderNote() {}
	
	public OrderNote(boolean enabled, LocalDate date, char turn, int numStudents, Matter matter,
			String observations, User user) {
		super();		
		this.enabled = enabled;
		this.date = date;
		this.turn = turn;
		this.numStudents = numStudents;
		this.matter = matter;
		this.observations = observations;
		this.user = user;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;	
	}

	public int getId() {
		return id;
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
  
	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

  public char getTurn() {
		return turn;
	}

	public void setTurn(char turn) {
		this.turn = turn;
	}

	public int getNumStudents() {
		return numStudents;
	}

	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}

	public Matter getMatter() {
		return matter;
	}

	public void setMatter(Matter matter) {
		this.matter = matter;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
 	
}
