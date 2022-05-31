package com.unla.Grupo07OO22022.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.Grupo07OO22022.entities.Department;

@Repository("departmentRepository")
public interface IDepartmentRepository extends JpaRepository<Department, Integer>{

	public abstract Department findById(int id);
	
	public abstract List<Department> findByEnabled(boolean enabled);
	
	public abstract Department findByName(String name);
	
	public abstract Department findByNameAndEnabled(String name, boolean enabled);
}
