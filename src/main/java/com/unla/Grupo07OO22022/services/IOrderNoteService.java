package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.Course;
import com.unla.Grupo07OO22022.entities.Final;
import com.unla.Grupo07OO22022.entities.OrderNote;
import com.unla.Grupo07OO22022.models.CourseModel;
import com.unla.Grupo07OO22022.models.FinalModel;
import com.unla.Grupo07OO22022.models.OrderNoteModel;

public interface IOrderNoteService {

	public List<OrderNote> getAll();
	
	public OrderNote findById(int id);	
	 
	public List<OrderNote> findByEnabled(boolean enabled, boolean course);
	
	public OrderNoteModel insertOrUpdate(OrderNote orderNote);
	
	public CourseModel insertOrUpdateCourse(Course course);

	public FinalModel insertOrUpdateFinal(Final finalr);
	
	public boolean remove(int id);

}
