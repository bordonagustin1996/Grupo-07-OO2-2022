package com.unla.Grupo07OO22022.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.unla.Grupo07OO22022.entities.User;
import com.unla.Grupo07OO22022.repositories.IUserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return new CustomUserDetails(user);
	}
	
}
