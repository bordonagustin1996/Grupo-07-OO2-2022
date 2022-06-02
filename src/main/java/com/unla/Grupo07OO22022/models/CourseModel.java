package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseModel extends OrderNoteModel {

	private String code;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	public CourseModel() {
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
