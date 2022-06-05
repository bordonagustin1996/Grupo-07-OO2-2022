package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.models.UserModel;

public interface IUserService {

	public List<User> getAll();
	
	public User findById(int id);	
	
	public UserModel insertOrUpdate(User user);
	
	public User findByName(String name);
	
	public boolean remove(int id);

	User findByUsername(String userName);
	
}
