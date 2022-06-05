package com.unla.Grupo07OO22022.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.OrderNote;
import com.unla.Grupo07OO22022.entities.Space;
import com.unla.Grupo07OO22022.models.SpaceModel;

public interface ISpaceService {
	
	public List<Space> getAll();
	
	public Space findById(int id);

	public SpaceModel insertOrUpdate(Space space);
	
	public boolean remove(int id);
	
	public Space findByDateAndTurnAndClassroom(LocalDate date, char turn, Classroom classroom);	

	public void addByDates(List<Classroom> classrooms, LocalDate startDate, LocalDate endDate);
	
	public Space findByDateAndTurnAndClassroomAndFree(LocalDate date, char turn, Classroom classroom, boolean free);
		
	public List<Space> saveAll(List<Space> space);
	
	public List<Space> getSpace(LocalDate startDate, Classroom classroom, char turn, int ftfPercentage, boolean evenWeek);
	
	public List<Space> findByOrderNoteOrderByDateAsc(OrderNote ordernote);
	
}
