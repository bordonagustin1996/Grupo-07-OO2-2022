package com.unla.Grupo07OO22022.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.OrderNote;
import com.unla.Grupo07OO22022.entities.Space;

@Repository("spaceRepository")
public interface ISpaceRepository extends JpaRepository<Space, Integer>{

	public abstract Space findById(int id);
	
	public abstract Space findByDateAndTurnAndClassroom(LocalDate date, char turn, Classroom classroom);
	
	public abstract Space findByDateAndTurnAndClassroomAndFree(LocalDate date, char turn, Classroom classroom, boolean free);
	
	public abstract List<Space> findByOrderNoteOrderByDateAsc(OrderNote ordernote);
	
}
