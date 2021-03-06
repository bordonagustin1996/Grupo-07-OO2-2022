package com.unla.Grupo07OO22022.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.Building;
import com.unla.Grupo07OO22022.models.BuildingModel;
import com.unla.Grupo07OO22022.repositories.IBuildingRepository;
import com.unla.Grupo07OO22022.services.IBuildingService;

@Service("buildingService")
public class BuildingService implements IBuildingService {
	
	@Autowired
	@Qualifier("buildingRepository")
	private IBuildingRepository buildingRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<Building> getAll() {
		return buildingRepository.findAll();
	}

	@Override
	public Building findById(int id) {
		return buildingRepository.findById(id);
	}

	@Override
	public BuildingModel insertOrUpdate(Building building) {
		if(building.getId() > 0) {
			Building buildingOld = buildingRepository.findById(building.getId());
			building.setCreatedAt(buildingOld.getCreatedAt());
		}
		return modelMapper.map(buildingRepository.save(building), BuildingModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			buildingRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
