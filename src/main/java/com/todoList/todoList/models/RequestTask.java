package com.todoList.todoList.models;

import java.util.Date;

public record RequestTask(
		Long id,
		String title, 
		String description,
		Boolean completed,
		Date deadLine
		) {

	public Long id() {
		return id;
	}

	public String title() {
		return title;
	}

	public String description() {
		return description;
	}

	public Date deadLine() {
		return deadLine;
	}

	public Boolean completed() {
		return completed;
	}
	
}
