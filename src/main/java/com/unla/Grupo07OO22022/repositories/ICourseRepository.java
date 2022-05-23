package com.unla.Grupo07OO22022.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.Grupo07OO22022.entities.Course;


@Repository("courseRepository")
public interface ICourseRepository extends JpaRepository<Course, Integer>{

	public abstract Course findById(int id);
	
	public abstract List<Course> findByEnabled(boolean enabled);	
}
