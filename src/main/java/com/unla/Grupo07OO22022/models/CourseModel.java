package com.unla.Grupo07OO22022.models;

public class CourseModel extends OrderNoteModel{
	
	protected int idCourse;
	
	protected String code;

	public CourseModel() {
		super();
	}

	public CourseModel(int idCourse, String code) {
		super();
		setIdCourse(idCourse);
		this.code = code;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
