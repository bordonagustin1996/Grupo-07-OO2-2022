package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Course;
import com.unla.Grupo07OO22022.models.CourseModel;

public interface ICourseService {
	
    public List<Course> getAll();
	
	public Course findById(int id);
	
	public List<Course> findByEnabled(boolean enabled);
	
	public CourseModel insertOrUpdate(Course userRole);
	
	public boolean remove(int id);

}
