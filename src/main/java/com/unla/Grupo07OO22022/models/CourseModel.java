package com.unla.Grupo07OO22022.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseModel extends OrderNoteModel {

	private String code;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	private int ftfPercentage;
	
	private boolean evenWeek;
	
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

	public int getFtfPercentage() {
		return ftfPercentage;
	}

	public void setFtfPercentage(int ftfPercentage) {
		this.ftfPercentage = ftfPercentage;
	}

	public boolean isEvenWeek() {
		return evenWeek;
	}

	public void setEvenWeek(boolean evenWeek) {
		this.evenWeek = evenWeek;
	}

}
