package com.unla.Grupo07OO22022.services.implementation;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.models.UserModel;
import com.unla.Grupo07OO22022.repositories.IUserRepository;


@Service("userService")
public class UserService {
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
		
	public User findById(int id) {
		return this.userRepository.findById(id);
	}

	public UserModel insertOrUpdate(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User userNew = this.userRepository.save(user);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities = new ArrayList<>(auth.getAuthorities());
		authorities.clear();
		authorities.add(new SimpleGrantedAuthority(userNew.getUserRole().getName()));
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
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
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
