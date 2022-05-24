package com.unla.Grupo07OO22022.services;

import java.util.List;

import com.unla.Grupo07OO22022.entities.OrderNote;
import com.unla.Grupo07OO22022.models.OrderNoteModel;

public interface IOrderNoteService {
	
public List<OrderNote> getAll();
	
	public OrderNote findById(int id);
	
	public List<OrderNote> findByEnabled(boolean enabled);
	
	public OrderNoteModel insertOrUpdate(OrderNote userRole);
	
	public boolean remove(int id);

}
