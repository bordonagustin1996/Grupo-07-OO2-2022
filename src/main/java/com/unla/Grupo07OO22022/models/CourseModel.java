package com.unla.Grupo07OO22022.models;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CourseModel extends OrderNoteModel{

	private String code;
	
	public CourseModel() {}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
