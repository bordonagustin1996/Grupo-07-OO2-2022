package com.unla.Grupo07OO22022.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.unla.Grupo07OO22022.entities.Department;
import com.unla.Grupo07OO22022.models.DepartmentModel;
import com.unla.Grupo07OO22022.repositories.IDepartmentRepository;
import com.unla.Grupo07OO22022.services.IDepartmentService;

public class DepartmentService implements IDepartmentService{
	
	@Autowired
	@Qualifier("departmentRepository")
	private IDepartmentRepository departmentRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Department> getAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Department findById(int id) {
		return departmentRepository.findById(id);
	}

	@Override
	public List<Department> findByEnabled(boolean enabled) {
		return departmentRepository.findByEnabled(enabled);
	}

	@Override
	public DepartmentModel insertOrUpdate(Department departmentParam) {
		Department nuevoDepartment = departmentRepository.save(departmentParam);
		return modelMapper.map(nuevoDepartment,DepartmentModel.class );
	}

	@Override
	public boolean remove(int id) {
		try {
			
			departmentRepository.deleteById(id);
		return true;
		
		}catch(Exception e) {
			return false;
		}
	}


}
