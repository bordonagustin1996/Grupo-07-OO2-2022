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

	public LocalDate getExamDate() {		
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}
	
}