package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Building;
import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.Laboratory;
import com.unla.Grupo07OO22022.entities.Traditional;
import com.unla.Grupo07OO22022.models.LaboratoryModel;
import com.unla.Grupo07OO22022.models.TraditionalModel;

public interface IClassroomService {
	
	public List<Classroom> getAll();
	
	public Classroom findById(int id);
	
	public List<Classroom> findByBuilding(Building building);
	
	public LaboratoryModel insertOrUpdate(Laboratory laboratory);
	
	public TraditionalModel insertOrUpdate(Traditional traditional);
	
	public boolean remove(int id);

}
