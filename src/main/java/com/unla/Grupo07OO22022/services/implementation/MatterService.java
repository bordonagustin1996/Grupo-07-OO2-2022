package com.unla.Grupo07OO22022.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.unla.Grupo07OO22022.entities.Matter;
import com.unla.Grupo07OO22022.models.MatterModel;
import com.unla.Grupo07OO22022.repositories.IMatterRepository;
import com.unla.Grupo07OO22022.services.IMatterService;

public class MatterService implements IMatterService{
	
	@Autowired
	@Qualifier("matterRepository")
	private IMatterRepository matterRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Matter> getAll() {
		return matterRepository.findAll();
	}

	@Override
	public Matter findById(int id) {
		return matterRepository.findById(id);
	}

	@Override
	public List<Matter> findByEnabled(boolean enabled) {
		return matterRepository.findByEnabled(enabled);
	}

	@Override
	public MatterModel insertOrUpdate(Matter matterParam) {
		Matter nuevoMatter = matterRepository.save(matterParam);
		return modelMapper.map(nuevoMatter,MatterModel.class );
	}

	@Override
	public boolean remove(int id) {
		try {
			
			matterRepository.deleteById(id);
		return true;
		
		}catch(Exception e) {
			return false;
		}
	}

}
