package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Department;
import com.unla.Grupo07OO22022.models.DepartmentModel;

public interface IDepartmentService {
	
    public List<Department> getAll();
	
	public Department findById(int id);
	
	public List<Department> findByEnabled(boolean enabled);
	
	public DepartmentModel insertOrUpdate(Department department);
	
	public boolean remove(int id);

}
