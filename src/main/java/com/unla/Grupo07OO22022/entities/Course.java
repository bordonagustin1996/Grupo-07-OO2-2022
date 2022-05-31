package com.unla.Grupo07OO22022.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "course")
public class Course extends OrderNote{

	@Column(name="code")
	private String code;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="start_date")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="day_of_week")
	private int dayOfWeek;
	
	public Course() {}
	
	public Course(int id, boolean enabled, LocalDate date, char turn, int numStudents, Matter matter,
			String observations, User user, String code, LocalDate startDate, LocalDate endDate, int dayOfWeek) {
		super(enabled, date, turn, numStudents, matter, observations, user);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}	
	  
}