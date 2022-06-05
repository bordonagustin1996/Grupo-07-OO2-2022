package com.unla.Grupo07OO22022.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.UserRole;
import com.unla.Grupo07OO22022.models.UserRoleModel;
import com.unla.Grupo07OO22022.repositories.IUserRoleRepository;
import com.unla.Grupo07OO22022.services.IUserRoleService;

@Service("userRoleService")
public class UserRoleService implements IUserRoleService {
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<UserRole> getAll() {
		return userRoleRepository.findAll();
	}
	
	@Override
	public UserRole findById(int id) {
		return userRoleRepository.findById(id);
	}
	
	@Override
	public UserRoleModel insertOrUpdate(UserRole userRole) {
		UserRole userRoleNew = userRoleRepository.save(userRole);
		return modelMapper.map(userRoleNew, UserRoleModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			userRoleRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
