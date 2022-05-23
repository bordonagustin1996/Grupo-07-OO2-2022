package com.unla.Grupo07OO22022.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Course")
@SQLDelete(sql = "UPDATE Course SET enabled = false WHERE id=?")
@PrimaryKeyJoinColumn(name = "id")
public class Course extends OrderNote{
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "enabled")
	private boolean enabled = true;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Course() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
