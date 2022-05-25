package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Final;
import com.unla.Grupo07OO22022.models.FinalModel;

public interface IFinalService {
	
    public List<Final> getAll();
	
	public Final findById(int id);
	
	public List<Final> findByEnabled(boolean enabled);
	
	public FinalModel insertOrUpdate(Final userRole);
	
	public boolean remove(int id);

}
