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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.entities.UserRole;
import com.unla.Grupo07OO22022.models.UserModel;
import com.unla.Grupo07OO22022.repositories.IUserRepository;
import com.unla.Grupo07OO22022.services.IUserService;

@Service("userService")
public class UserService implements UserDetailsService, IUserService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}
	
	@Override
	public User findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public UserModel insertOrUpdate(User user) {
		if (user.getId() > 0) {
			User userOld = userRepository.findById(user.getId());
			if (user.getPassword().isBlank()) {
				user.setPassword(userOld.getPassword());
			} else {
				user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			}
			user.setCreatedAt(userOld.getCreatedAt());
		} else {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		return modelMapper.map(userRepository.save(user), UserModel.class);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	private org.springframework.security.core.userdetails.User buildUser(User user, List<GrantedAuthority> grantedAuthorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(UserRole userRole) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getName()));
		return grantedAuthorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameAndFetchRole(username);
		if (user == null) {
            throw new UsernameNotFoundException(username);
        }
		return buildUser(user, buildGrantedAuthorities(user.getUserRole()));
	}

}
