package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.unla.Grupo07OO22022.entities.Career;

public class MatterModel {
	
	private int id;
	
	@Min(1)
	@Max(9999)
	private int code;
	
	@NotBlank 
	private String name;
	
	private Career career;

	public MatterModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}
  
}
