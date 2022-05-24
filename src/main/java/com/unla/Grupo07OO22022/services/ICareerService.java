package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Career;
import com.unla.Grupo07OO22022.models.CareerModel;

public interface ICareerService {
	
    public List<Career> getAll();
	
	public Career findById(int id);
	
	public List<Career> findByEnabled(boolean enabled);
	
	public CareerModel insertOrUpdate(Career userRole);
	
	public boolean remove(int id);

}
