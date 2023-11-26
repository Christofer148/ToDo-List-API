package com.todoList.todoList.models;

import java.util.Date;

public record RequestTask(
		String title, 
		String description,
		Date deadLine
		) {

	public String title() {
		return title;
	}

	public String description() {
		return description;
	}

	public Date deadLine() {
		return deadLine;
	}
	
}
