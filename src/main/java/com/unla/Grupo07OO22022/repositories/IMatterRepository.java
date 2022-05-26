package com.unla.Grupo07OO22022.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.Matter;

@Repository("matterRepository")
public interface IMatterRepository extends JpaRepository<Matter, Integer>{
	
	public abstract Matter findById(int id);
	
	public abstract List<Matter> findByEnabled(boolean enabled);
	
	public abstract Matter findByName(String name);	
  
}
