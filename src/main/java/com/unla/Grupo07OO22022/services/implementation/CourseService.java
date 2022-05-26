//package com.unla.Grupo07OO22022.services.implementation;
//
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import com.unla.Grupo07OO22022.entities.Course;
//import com.unla.Grupo07OO22022.entities.Final;
//import com.unla.Grupo07OO22022.models.CourseModel;
//import com.unla.Grupo07OO22022.models.FinalModel;
//import com.unla.Grupo07OO22022.repositories.ICourseRepository;
//import com.unla.Grupo07OO22022.services.ICourseService;
//
//public class CourseService implements ICourseService{
//
//	@Autowired
//	@Qualifier("courseRepository")
//	private ICourseRepository courseRepository;
//	
//	private ModelMapper modelMapper = new ModelMapper();
//	
//	@Override
//	public List<Course> getAll() {
//		return courseRepository.findAll();
//	}
//
//	@Override
//	public Course findById(int id) {
//		return courseRepository.findById(id);
//	}
//
//	@Override
//	public List<Course> findByEnabled(boolean enabled) {
//		return courseRepository.findByEnabled(enabled);
//	}
//
//	@Override
//	public CourseModel insertOrUpdate(Course finalParam) {
//		Course nuevoCourse = courseRepository.save(finalParam);
//		return modelMapper.map(nuevoCourse, CourseModel.class);
//	}
//
//	@Override
//	public boolean remove(int id) {
//		try {
//			
//			courseRepository.deleteById(id);
//		return true;
//		
//		}catch(Exception e) {
//			return false;
//		}
//	}
//
//}
