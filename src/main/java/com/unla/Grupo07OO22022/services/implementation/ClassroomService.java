package com.unla.Grupo07OO22022.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.Building;
import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.Laboratory;
import com.unla.Grupo07OO22022.entities.Traditional;
import com.unla.Grupo07OO22022.models.LaboratoryModel;
import com.unla.Grupo07OO22022.models.TraditionalModel;
import com.unla.Grupo07OO22022.repositories.IClassroomRepository;
import com.unla.Grupo07OO22022.services.IClassroomService;

@Service("classroomService")
public class ClassroomService implements IClassroomService {

	@Autowired
	@Qualifier("classroomRepository")
	private IClassroomRepository classroomRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<Classroom> getAll() {
		return classroomRepository.findAll();
	}
	
	@Override
	public Classroom findById(int id) {
		return classroomRepository.findById(id);
	}

	@Override
	public List<Classroom> findByBuilding(Building building) {		
		return classroomRepository.findByBuilding(building);
	}
	
	@Override
	public LaboratoryModel insertOrUpdate(Laboratory laboratory) {
		if(laboratory.getId() > 0) {
			Laboratory laboratoryOld = (Laboratory) classroomRepository.findById(laboratory.getId());
			laboratory.setCreatedAt(laboratoryOld.getCreatedAt());
		}
		return modelMapper.map(classroomRepository.save(laboratory), LaboratoryModel.class);
	}
	
	@Override
	public TraditionalModel insertOrUpdate(Traditional traditional) {
		if(traditional.getId() > 0) {
			Traditional traditionalOld = (Traditional) classroomRepository.findById(traditional.getId());
			traditional.setCreatedAt(traditionalOld.getCreatedAt());
		}
		return modelMapper.map(classroomRepository.save(traditional), TraditionalModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			classroomRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
