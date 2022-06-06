package com.unla.Grupo07OO22022.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.unla.Grupo07OO22022.entities.UserRole;

public class UserModel {
	
	private int id;
	
	@NotBlank(message = "El nombre es requerido")
	private String name;
	
	private String surname;
	
	private String documentType;
	
	@Min(1)
	private long documentNumber;
	
	private String email;
	
	@NotBlank(message = "El usuario es requerido")	
	private String username;
		
	private String password;		

	private UserRole userRole;
	
	public UserModel() {}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
