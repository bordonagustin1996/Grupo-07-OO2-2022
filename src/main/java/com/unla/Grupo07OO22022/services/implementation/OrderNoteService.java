package com.unla.Grupo07OO22022.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo07OO22022.entities.Course;
import com.unla.Grupo07OO22022.entities.Final;
import com.unla.Grupo07OO22022.entities.OrderNote;
import com.unla.Grupo07OO22022.models.CourseModel;
import com.unla.Grupo07OO22022.models.FinalModel;
import com.unla.Grupo07OO22022.repositories.ICourseRepository;
import com.unla.Grupo07OO22022.repositories.IFinalRepository;
import com.unla.Grupo07OO22022.repositories.IOrderNoteRepository;
import com.unla.Grupo07OO22022.services.IOrderNoteService;

@Service("orderNoteService")
public class OrderNoteService implements IOrderNoteService{
	
	@Autowired
	@Qualifier("orderNoteRepository")
	private IOrderNoteRepository orderNoteRepository;
	
	@Autowired
	@Qualifier("finalRepository")
	private IFinalRepository finalRepository;
	
	@Autowired
	@Qualifier("courseRepository")
	private ICourseRepository courseRepository;
	
	protected ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<OrderNote> getAll() {		
		return orderNoteRepository.findAll();
	}

	@Override
	public OrderNote findById(int id) {
		return orderNoteRepository.findById(id);
	}

	@Override
	public List<OrderNote> getAll(boolean course) {
		List<OrderNote> orders = new ArrayList<OrderNote>();
		for(OrderNote o: orderNoteRepository.findAll()) {
			if(o instanceof Course == course) {
				orders.add(o);
			}
		}		
		return orders;
	}

	@Override
	public boolean remove(int id) {
		try {
			orderNoteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public CourseModel insertOrUpdateCourse(Course course) {
		if (course.getId() > 0) {
			Course courseOld = (Course) courseRepository.findById(course.getId());
			course.setCreatedAt(courseOld.getCreatedAt());
		}
		return modelMapper.map(courseRepository.save(course), CourseModel.class);
	}
	
	
	public FinalModel insertOrUpdateFinal(Final finalr) {
		if (finalr.getId() > 0) {
			Final finalOld = (Final) finalRepository.findById(finalr.getId());
			finalr.setCreatedAt(finalOld.getCreatedAt());
		}
		return modelMapper.map(finalRepository.save(finalr), FinalModel.class);
	}
	
}
