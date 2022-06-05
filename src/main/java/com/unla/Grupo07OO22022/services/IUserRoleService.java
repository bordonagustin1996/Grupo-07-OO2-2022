package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.UserRole;
import com.unla.Grupo07OO22022.models.UserRoleModel;

public interface IUserRoleService {

	public List<UserRole> getAll();
	
	public UserRole findById(int id);
	
	public UserRoleModel insertOrUpdate(UserRole userRole);
	
	public boolean remove(int id);
	
}
