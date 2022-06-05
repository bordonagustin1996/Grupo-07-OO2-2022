package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Building;
import com.unla.Grupo07OO22022.models.BuildingModel;

public interface IBuildingService {
	
	public List<Building> getAll();
	
	public Building findById(int id);
	
	public BuildingModel insertOrUpdate(Building building);
	
	public boolean remove(int id);

}
