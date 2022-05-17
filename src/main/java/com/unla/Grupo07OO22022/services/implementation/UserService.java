package com.unla.Grupo07OO22022.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.models.UserModel;
import com.unla.Grupo07OO22022.repositories.IUserRepository;
import com.unla.Grupo07OO22022.services.IUserService;

@Service("userService")
public class UserService implements IUserService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public User findById(int id) {
		return this.userRepository.findById(id);
	}

	@Override
	public UserModel insertOrUpdate(User user) {
		User userNew = this.userRepository.save(user);		
		return this.modelMapper.map(userNew, UserModel.class);
	}

	@Override
	public List<User> getAll() { 
		// Filtro solo los que estan activos
		// TODO Verificar si se puede hacer mediante funcionalidad nativa de spring
		List<User> userEnabled = new ArrayList<User>();
		for(User user : this.userRepository.findAll()) {
			if(user.isEnabled()) {
				userEnabled.add(user);
			}
		}		
		return userEnabled;
	}
	
	@Override
	public boolean remove(int id) {
		try {
			userRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
