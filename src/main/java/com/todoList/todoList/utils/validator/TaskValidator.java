package com.todoList.todoList.utils.validator;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.todoList.todoList.models.RequestTask;
import com.todoList.todoList.utils.exceptions.ApiUnprocessableEntity;

@Component
public class TaskValidatorImplement implements TaskValidator{

	@Override
	public void validate(RequestTask task) throws ApiUnprocessableEntity {
		if(task.title() == null || task.title().isBlank()) {
			this.message("Title of the task is mandatory");
		}
		if(task.description() == null) {
			this.message("Description can't be null");
		}
		if(task.deadLine() != null && task.deadLine().before(Date.valueOf(LocalDate.now()))) {
			this.message("The dead-line must be after current Date");
		}
	}
	
	private void message(String message) throws ApiUnprocessableEntity{
		throw new ApiUnprocessableEntity(message);
	}

}
