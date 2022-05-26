package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Matter;
import com.unla.Grupo07OO22022.models.MatterModel;

public interface IMatterService {

	public List<Matter> getAll();
	
	public Matter findById(int id);
	
	public List<Matter> findByEnabled(boolean enabled);

	public MatterModel insertOrUpdate(Matter matter);
	
	public boolean remove(int id);	
}
