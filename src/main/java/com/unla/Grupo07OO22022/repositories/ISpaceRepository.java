package com.unla.Grupo07OO22022.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.Space;

@Repository("spaceRepository")
public interface ISpaceRepository extends JpaRepository<Space, Integer>{

	public abstract Space findById(int id);
	
	public abstract List<Space> findByEnabled(boolean enabled);
	
	public abstract Space findByDateAndTurnAndClassroomAndEnabled(LocalDate date, char turn, Classroom classroom, boolean enabled);
	
	public abstract Space findByDateAndTurnAndClassroomAndFreeAndEnabled(LocalDate date, char turn, Classroom classroom,boolean free, boolean enabled);
	
}
