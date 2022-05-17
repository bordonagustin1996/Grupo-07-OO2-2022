package com.unla.Grupo07OO22022.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unla.Grupo07OO22022.entities.User;



@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable>{

	public abstract User findById(int id);
	
	//@Query("FROM user WHERE enabled = true")
	public abstract List<User> findByEnabled(boolean enabled);
	
}
