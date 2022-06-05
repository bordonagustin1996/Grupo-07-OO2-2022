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
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "order_note")
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE order_note SET enabled = false WHERE id = ?")
@Where(clause = "enabled = true")
public class OrderNote {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@Column(name = "turn")
	private char turn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;

	@Column(name = "number_students")
	private int numberStudents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "matter_id")
	private Matter matter;

	@Column(name = "observations")
	private String observations;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "confirmed")
	private boolean confirmed;

	@Column(name = "enabled")
	private boolean enabled = true;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public OrderNote() {}

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

	public char getTurn() {
		return turn;
	}

	public void setTurn(char turn) {
		this.turn = turn;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public int getNumberStudents() {
		return numberStudents;
	}

	public void setNumberStudents(int numberStudents) {
		this.numberStudents = numberStudents;
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
