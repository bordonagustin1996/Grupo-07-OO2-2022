package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Department;
import com.unla.Grupo07OO22022.models.DepartmentModel;

public interface IDepartmentService {
	
    public List<Department> getAll();
	
	public Department findById(int id);	
	
	public DepartmentModel insertOrUpdate(Department department);
	
	public Department findByName(String name);
	
	public boolean remove(int id);
	
}
