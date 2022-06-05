package com.unla.Grupo07OO22022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo07OO22022.entities.UserRole;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {

	public abstract UserRole findById(int id);
	
}
