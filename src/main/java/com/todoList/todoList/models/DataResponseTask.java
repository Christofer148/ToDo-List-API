package com.todoList.todoList.models;

import java.util.Date;

public record DataResponseTask(
		Long id,
		String title, 
		String description, 
		Boolean completed, 
		Date deadLine) {

}
