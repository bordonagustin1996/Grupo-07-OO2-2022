package com.unla.Grupo07OO22022.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.Grupo07OO22022.entities.Space;

@Repository("spaceRepository")
public interface ISpaceRepository extends JpaRepository<Space, Integer>{

	public abstract Space findById(int id);
	
	public abstract List<Space> findByEnabled(boolean enabled);	
	
}
