package com.unla.Grupo07OO22022.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course extends OrderNote{

	@Column(name="code")
	private String code;
	
	public Course() {}
	
	public Course(int id, boolean enabled, LocalDate date, char turn, int numStudents, Matter matter,
			String observations, User user, String code) {
		super(enabled, date, turn, numStudents, matter, observations, user);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
  
}