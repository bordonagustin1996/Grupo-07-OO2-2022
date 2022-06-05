package com.unla.Grupo07OO22022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.Grupo07OO22022.entities.Career;


@Repository("careerRepository")
public interface ICareerRepository extends JpaRepository<Career, Integer>{

	public abstract Career findById(int id);
	
}
