package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Space;
import com.unla.Grupo07OO22022.models.SpaceModel;

public interface ISpaceService {
	
	public List<Space> getAll();
	
	public Space findById(int id);
	
	public List<Space> findByEnabled(boolean enabled);

	public SpaceModel insertOrUpdate(Space space);
	
	public boolean remove(int id);	
	
}