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
import com.unla.Grupo07OO22022.models.OrderNoteModel;
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
	public OrderNoteModel insertOrUpdate(OrderNote orderNote) {
		OrderNote orderNoteNew = orderNoteRepository.save(orderNote);
		return modelMapper.map(orderNoteNew, OrderNoteModel.class);
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
		Course courseNew = courseRepository.save(course);
		return modelMapper.map(courseNew, CourseModel.class);
	}
	
	
	public FinalModel insertOrUpdateFinal(Final finalr) {
		Final finalNew = finalRepository.save(finalr);
		return modelMapper.map(finalNew, FinalModel.class);
	}
	
}
