package com.unla.Grupo07OO22022.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "final")
public class Final extends OrderNote{
	
	@Column(name="exam_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate examDate;
	
	public Final() {}
	
	public Final(boolean enabled, LocalDate date, char turn, int numStudents, Matter matter,
			String observations, User user, LocalDate examDate) {
		super(enabled, date, turn, numStudents, matter, observations, user);
		this.examDate = examDate;
	}

	public LocalDate getExamDate() {		
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}	
	
}
