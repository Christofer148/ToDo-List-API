package com.todoList.todoList.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	private Boolean completed;
	
	private Boolean deleted;

	@Temporal(TemporalType.DATE)
	private Date deadLine;
	
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
	private Date lastModification;

	public Task(Long id, String title, String description, Boolean completed, Date deadLine) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.completed = completed;
		this.deadLine = deadLine;
	}
	
	public Task() {}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
