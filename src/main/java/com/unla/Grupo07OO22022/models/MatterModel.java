package com.unla.Grupo07OO22022.models;

public class MatterModel {

	private int idMatter;

	private String code;
	
	private String name;

	public MatterModel() {
		super();
	}

	public MatterModel(int idMatter, String code, String name) {
		super();
		setIdMatter(idMatter);
		this.code = code;
		this.name = name;
	}

	public int getIdMatter() {
		return idMatter;
	}

	public void setIdMatter(int idMatter) {
		this.idMatter = idMatter;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
