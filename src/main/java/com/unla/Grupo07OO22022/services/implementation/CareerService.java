package com.unla.Grupo07OO22022.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.Career;
import com.unla.Grupo07OO22022.models.CareerModel;
import com.unla.Grupo07OO22022.repositories.ICareerRepository;
import com.unla.Grupo07OO22022.services.ICareerService;

@Service("careerService")
public class CareerService implements ICareerService{
	
	@Autowired
	@Qualifier("careerRepository")
	private ICareerRepository careerRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Career> getAll() {
		return careerRepository.findAll();
	}

	@Override
	public Career findById(int id) {
		return careerRepository.findById(id);
	}

	@Override
	public CareerModel insertOrUpdate(Career career) {
		if(career.getId() > 0) {
			Career careerOld = careerRepository.findById(career.getId());
			career.setCreatedAt(careerOld.getCreatedAt());
		}		
		return modelMapper.map(careerRepository.save(career), CareerModel.class );
	}

	@Override
	public boolean remove(int id) {
		try {			
			careerRepository.deleteById(id);
		return true;		
		}catch(Exception e) {
			return false;
		}
	}

}
