package com.unla.Grupo07OO22022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.Building;

@Repository("buildingRepository")
public interface IBuildingRepository extends JpaRepository<Building, Integer> {

	public abstract Building findById(int id);
	
}
