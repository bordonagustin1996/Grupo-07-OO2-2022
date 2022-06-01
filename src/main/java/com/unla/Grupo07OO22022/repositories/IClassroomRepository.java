package com.unla.Grupo07OO22022.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.Building;
import com.unla.Grupo07OO22022.entities.Classroom;

@Repository("classroomRepository")
public interface IClassroomRepository extends JpaRepository<Classroom, Integer> {

	public abstract Classroom findById(int id);
	
	public abstract List<Classroom> findByEnabled(boolean enabled);
	
	public abstract List<Classroom> findByBuildingAndEnabled(Building building, boolean enabled);
	
}
