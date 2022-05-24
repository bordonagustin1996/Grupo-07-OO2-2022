package com.unla.Grupo07OO22022.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.unla.Grupo07OO22022.entities.Final;
import com.unla.Grupo07OO22022.models.FinalModel;
import com.unla.Grupo07OO22022.repositories.IFinalRepository;
import com.unla.Grupo07OO22022.services.IFinalService;

public class FinalService implements IFinalService{

	@Autowired
	@Qualifier("finalRepository")
	private IFinalRepository finalRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Final> getAll() {
		return finalRepository.findAll();
	}

	@Override
	public Final findById(int id) {
		return finalRepository.findById(id);
	}

	@Override
	public List<Final> findByEnabled(boolean enabled) {
		return finalRepository.findByEnabled(enabled);
	}

	@Override
	public FinalModel insertOrUpdate(Final finalParam) {
		Final nuevoFinal = finalRepository.save(finalParam);
		return modelMapper.map(nuevoFinal, FinalModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			
		finalRepository.deleteById(id);
		return true;
		
		}catch(Exception e) {
			return false;
		}
	}

}
