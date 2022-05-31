package com.unla.Grupo07OO22022.services.implementation;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.Space;
import com.unla.Grupo07OO22022.models.SpaceModel;
import com.unla.Grupo07OO22022.repositories.ISpaceRepository;
import com.unla.Grupo07OO22022.services.IClassroomService;
import com.unla.Grupo07OO22022.services.ISpaceService;

@Service("spaceService")
public class SpaceService implements ISpaceService {

	@Autowired
	@Qualifier("spaceRepository")
	private ISpaceRepository spaceRepository;
	
	@Autowired
	@Qualifier("classroomService")
	private IClassroomService classroomService;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<Space> getAll() {
		return spaceRepository.findAll();
	}

	@Override
	public Space findById(int id) {
		return spaceRepository.findById(id);
	}

	@Override
	public List<Space> findByEnabled(boolean enabled) {
		return spaceRepository.findByEnabled(true);
	}

	@Override
	public SpaceModel insertOrUpdate(Space space) {
		Space spaceNew = spaceRepository.save(space);
		return modelMapper.map(spaceNew, SpaceModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			spaceRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Space findByDateAndTurnAndClassroom(LocalDate date, char turn, Classroom classroom) {
		return spaceRepository.findByDateAndTurnAndClassroomAndEnabled(date, turn, classroom, true);
	}

}
