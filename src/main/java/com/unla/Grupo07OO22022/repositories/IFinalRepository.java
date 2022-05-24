package com.unla.Grupo07OO22022.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.Final;

@Repository("finalRepository")
public interface IFinalRepository extends JpaRepository<Final, Integer>{
	
	public abstract Final findById(int id);
	
	public abstract List<Final> findByEnabled(boolean enabled);	

}
