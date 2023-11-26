package com.todoList.todoList.models;

import java.util.Date;

public record DataListedTask(
		Long id,
		String title, 
		String description, 
		Boolean completed, 
		Date deadLine
		) {
		
	public DataListedTask(Task task) {
		this(
				task.getId(), 
				task.getTitle(), 
				task.getDescription(), 
				task.getCompleted(),
				task.getDeadLine()
				);
	}
}
