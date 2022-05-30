package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.NotBlank;

import com.unla.Grupo07OO22022.entities.UserRole;

public class UserModel {
	
	private int id;
	
	@NotBlank(message = "El nombre es requerido")
	private String name;
	
	private String surname;
	
	@NotBlank(message = "El usuario es requerido")	
	private String username;
	
	private String email;
	
	private String documentType;
		
	private String password;
	
	private long documentNumber;	

	private UserRole userRole;
	
	public UserModel() {}
	
	public UserModel(int id, String name, String surname, String email, String documentType, long documentNumber, String password) {
		this.setId(id);
		this.name = name;
		this.surname = surname;
		this.email = email;	
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(long documentNumber) {
		this.documentNumber = documentNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
