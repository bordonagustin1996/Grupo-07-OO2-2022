package com.unla.Grupo07OO22022.services.implementation;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.models.UserModel;
import com.unla.Grupo07OO22022.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public User findById(int id) {
		return this.userRepository.findById(id);
	}

	public UserModel insertOrUpdate(User user) {
		User userNew = this.userRepository.save(user);		
		return this.modelMapper.map(userNew, UserModel.class);
	}	
	
	public boolean remove(int id) {
		try {
			userRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public List<User> getAll() {		
		return userRepository.findAll();
	}

	public List<User> findByEnabled(boolean enable) {
		return userRepository.findByEnabled(enable);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = this.userRepository.findByUsername(username);		
		List<GrantedAuthority> userRole = new ArrayList<>();
		//userRole.add(new SimpleGrantedAuthority(usuario.getUserRole().getName())); //Asegurarse que el nombre sea ADMIN.
		userRole.add(new SimpleGrantedAuthority("ADMIN"));
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), userRole);
		return userDetails;
	}
}
