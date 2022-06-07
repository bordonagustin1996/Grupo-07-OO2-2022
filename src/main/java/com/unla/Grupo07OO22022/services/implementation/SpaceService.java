package com.unla.Grupo07OO22022.services.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.Classroom;
import com.unla.Grupo07OO22022.entities.OrderNote;
import com.unla.Grupo07OO22022.entities.Space;
import com.unla.Grupo07OO22022.models.SpaceModel;
import com.unla.Grupo07OO22022.repositories.ISpaceRepository;
import com.unla.Grupo07OO22022.services.ISpaceService;

@Service("spaceService")
public class SpaceService implements ISpaceService {

	@Autowired
	@Qualifier("spaceRepository")
	private ISpaceRepository spaceRepository;

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
	public SpaceModel insertOrUpdate(Space space) {
		if (space.getId() > 0) {
			Space spaceOld = spaceRepository.findById(space.getId());
			space.setCreatedAt(spaceOld.getCreatedAt());
		}
		return modelMapper.map(spaceRepository.save(space), SpaceModel.class);
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
		return spaceRepository.findByDateAndTurnAndClassroom(date, turn, classroom);
	}

	@Override
	public void addByDates(List<Classroom> classrooms, LocalDate startDate, LocalDate endDate) {
		LocalDate date = startDate;
		while (date.isBefore(endDate.plusDays(1))) {
			for (char turn : "MTN".toCharArray()) {
				for (Classroom classroom : classrooms) {
					if (findByDateAndTurnAndClassroom(date, turn, classroom) == null) {
						insertOrUpdate(new Space(date, turn, classroom));
					}
				}
			}
			date = date.plusDays(1);
		}
	}

	public Space findByDateAndTurnAndClassroomAndFree(LocalDate date, char turn, Classroom classroom, boolean free) {
		return spaceRepository.findByDateAndTurnAndClassroomAndFree(date, turn, classroom, free);
	}

	@Override
	public List<Space> saveAll(List<Space> space) {
		return spaceRepository.saveAll(space);
	}

	@Override
	public List<Space> getSpace(LocalDate startDate, Classroom classroom, char turn, int ftfPercentage, boolean evenWeek) {
		LocalDate date = (evenWeek && ftfPercentage == 50) ? startDate.plusWeeks(1) : startDate;
		LocalDate endDate = startDate.plusWeeks(14).plusDays(1);
		List<Space> spaces = new ArrayList<Space>();		
		while(date.isBefore(endDate)) {
			Space space = findByDateAndTurnAndClassroomAndFree(date, turn, classroom, true);					
			if(space != null) {
				space.setFree(false);
				spaces.add(space);
			}			
			date = (ftfPercentage == 50) ? date.plusWeeks(2): date.plusWeeks(1);
		}
		return spaces;
	}

	@Override
	public List<Space> findByOrderNoteOrderByDateAsc(OrderNote ordernote) {
		return spaceRepository.findByOrderNoteOrderByDateAsc(ordernote);
	}
	
}
