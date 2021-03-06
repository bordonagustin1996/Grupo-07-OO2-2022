package com.unla.Grupo07OO22022.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.OrderNote;

@Repository("orderNoteRepository")
public interface IOrderNoteRepository extends JpaRepository<OrderNote, Integer>{
	
	public abstract OrderNote findById(int id);
	
	public abstract OrderNote findByDate(LocalDate date);
  
}
